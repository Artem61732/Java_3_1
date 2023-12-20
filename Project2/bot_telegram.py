from aiogram import Bot, Dispatcher, executor, types
from aiogram.contrib.fsm_storage.memory import MemoryStorage
from aiogram.dispatcher import FSMContext
from aiogram.dispatcher.filters.state import State, StatesGroup
from aiogram.types import ReplyKeyboardMarkup, KeyboardButton
import os

bot = Bot(token=os.getenv('TOKEN'))
storage = MemoryStorage()
dp = Dispatcher(bot, storage=storage)

# Определение состояний
class Form(StatesGroup):
    sex = State()     # Пол пользователя
    goal = State()    # Цель пользователя
    age = State()     # Возраст
    weight = State()  # Вес
    height = State()  # Рост
    activity = State()  # Уровень активности

# Клавиатуры для выбора пола
sex_keyboard = ReplyKeyboardMarkup(resize_keyboard=True).add(
    KeyboardButton('Мужской'), KeyboardButton('Женский')
)

# Клавиатуры для выбора цели
goal_keyboard = ReplyKeyboardMarkup(resize_keyboard=True).add(
    KeyboardButton('Похудеть'), KeyboardButton('Поддерживать форму'), KeyboardButton('Набрать вес')
)

# Обработка команды /start
@dp.message_handler(commands=['start'], state='*')
async def cmd_start(message: types.Message):
    await Form.sex.set()
    await message.answer("Выберите ваш пол:", reply_markup=sex_keyboard)

# Обработка выбора пола
@dp.message_handler(state=Form.sex)
async def process_sex(message: types.Message, state: FSMContext):
    if message.text.lower() in ['мужской', 'женский']:
        async with state.proxy() as data:
            data['sex'] = message.text.lower()
        await Form.goal.set()
        await message.answer("Выберите вашу цель:", reply_markup=goal_keyboard)
    else:
        await message.answer("Выберите ваш пол с помощью кнопок:", reply_markup=sex_keyboard)

# Обработка цели
@dp.message_handler(state=Form.goal)
async def process_goal(message: types.Message, state: FSMContext):
    if message.text.lower() in ['похудеть', 'поддерживать форму', 'набрать вес']:
        async with state.proxy() as data:
            data['goal'] = message.text.lower()
        await Form.age.set()
        await message.answer("Введите ваш возраст:", reply_markup=types.ReplyKeyboardRemove())
    else:
        await message.answer("Выберите вашу цель с помощью кнопок:", reply_markup=goal_keyboard)

# Обработка возраста
@dp.message_handler(state=Form.age)
async def process_age(message: types.Message, state: FSMContext):
    if message.text.isdigit():
        age = int(message.text)
        if 1 <= age <= 130:
            async with state.proxy() as data:
                data['age'] = age
            await Form.weight.set()
            await message.answer("Введите ваш вес в кг:")
    else:
        await message.answer("Введите возраст цифрами в диапазоне от 1 до 130 лет:")

# Обработка веса
@dp.message_handler(state=Form.weight)
async def process_weight(message: types.Message, state: FSMContext):
    if message.text.isdigit():
        weight = int(message.text)
        if 3 <= weight <= 140:
            async with state.proxy() as data:
                data['weight'] = weight
            await Form.height.set()
            await message.answer("Введите ваш рост в см:")
    else:
        await message.answer("Введите вес цифрами в диапазоне от 3 до 140 кг:")

# Обработка роста
@dp.message_handler(state=Form.height)
async def process_height(message: types.Message, state: FSMContext):
    if message.text.isdigit():
        height = int(message.text)
        if 40 <= height <= 251:
            async with state.proxy() as data:
                data['height'] = height
            await Form.activity.set()
            await message.answer("Введите ваш уровень физической активности (1-7):\n"
                                "1: Нет физической нагрузки или минимальная\n"
                                "2: Тренировки 1 раз в неделю\n"
                                "3: Тренировки 2 раза в неделю\n"
                                "4: Тренировки 3-5 раз в неделю\n"
                                "5: Тренировки каждый день\n"
                                "6: Интенсивные тренировки каждый день или по 2 раза в день\n"
                                "7: Ежедневная физическая работа")
    else:
        await message.answer("Введите рост цифрами в диапазоне от 40 до 251 см:")

# Обработка уровня активности    
@dp.message_handler(state=Form.activity)
async def process_activity(message: types.Message, state: FSMContext):
    if message.text.isdigit():
        activity_input = message.text
        if activity_input in {'1', '2', '3', '4', '5', '6', '7'}:
            async with state.proxy() as data:
                data['activity'] = activity_input
            await calculate_and_send_calories(message, state)
    else:
        await message.answer("Введите число от 1 до 7, соответствующее вашему уровню активности:")

# Расчет калорийности
def calculate_calories(data):
    weight = data['weight']
    height = data['height']
    age = data['age']
    sex = data['sex']
    activity = {
        '1': 1.2, '2': 1.38, '3': 1.46, '4': 1.55, '5': 1.64, '6': 1.73, '7': 1.9
    }.get(data['activity'], 1.2)

    # Формула расчета DСI
    bmr = 10 * weight + 6.25 * height - 5 * age + (5 if sex == 'мужской' else -161)
    calories = bmr * activity

    # Модификация калорий в зависимости от цели
    if data['goal'] == 'похудеть':
        calories -= 500  # Примерное уменьшение на 500 ккал для дефицита
    elif data['goal'] == 'набрать вес':
        calories += 500  # Примерное увеличение на 500 ккал для профицита
    
    return calories

async def calculate_and_send_calories(message: types.Message, state: FSMContext):
    async with state.proxy() as data:
            calories = calculate_calories(data)
            await message.answer(f"Ваша суточная норма калорий: {calories:.2f} ккал")
            await state.finish()

# Запуск бота
if __name__ == '__main__':
    executor.start_polling(dp, skip_updates=True)