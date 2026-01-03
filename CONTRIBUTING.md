# Contributing to Student Portal

Дякуємо за інтерес до проєкту! Ми раді будь-якому внеску. 🎉

## 📋 Зміст

- [Як я можу допомогти?](#як-я-можу-допомогти)
- [Процес розробки](#процес-розробки)
- [Структура гілок](#структура-гілок)
- [Стандарти коду](#стандарти-коду)
- [Commit повідомлення](#commit-повідомлення)
- [Pull Request процес](#pull-request-процес)

---

## 💡 Як я можу допомогти?

### Повідомлення про баги

Якщо ви знайшли баг:

1. Перевірте, чи не створено вже [Issue](https://github.com/YOUR_USERNAME/StudentPortal/issues)
2. Якщо ні - створіть новий Issue з:
   - Чіткою та описовою назвою
   - Кроками для відтворення
   - Очікуваним результатом
   - Фактичним результатом
   - Скріншотами (якщо можливо)
   - Версією Android та пристроєм

**Шаблон Bug Report:**

```markdown
## Опис бага
[Короткий опис проблеми]

## Кроки для відтворення
1. Перейти до '...'
2. Натиснути на '...'
3. Побачити помилку

## Очікувана поведінка
[Що повинно статися]

## Фактична поведінка
[Що насправді відбувається]

## Середовище
- Android версія: 
- Пристрій: 
- Версія додатку: 

## Скріншоти
[Якщо є]
```

### Пропозиції нових функцій

Хочете запропонувати нову функцію?

1. Створіть Issue з міткою `enhancement`
2. Опишіть проблему, яку вирішує функція
3. Поділіться своєю ідеєю реалізації
4. Дочекайтесь обговорення

**Шаблон Feature Request:**

```markdown
## Опис функції
[Що ви хочете додати]

## Проблема, яку вирішує
[Чому це потрібно]

## Запропоноване рішення
[Як це може бути реалізовано]

## Альтернативи
[Інші можливі підходи]
```

### Покращення документації

- Виправлення друкарських помилок
- Додавання прикладів
- Покращення пояснень
- Переклади

---

## 🔄 Процес розробки

### 1. Fork репозиторію

```bash
# Клік на "Fork" на GitHub
# Клонування вашого fork
git clone https://github.com/YOUR_USERNAME/StudentPortal.git
cd StudentPortal
```

### 2. Створення гілки

```bash
# Оновлення main
git checkout main
git pull origin main

# Створення нової гілки
git checkout -b feature/my-new-feature
# або
git checkout -b bugfix/fix-some-bug
```

### 3. Внесення змін

- Пишіть чистий, зрозумілий код
- Дотримуйтесь стилю проєкту
- Додайте коментарі, де потрібно
- Оновіть документацію

### 4. Тестування

```bash
# Запуск unit tests
./gradlew test

# Запуск на емуляторі
./gradlew installDebug
```

### 5. Commit змін

```bash
git add .
git commit -m "feat: add new feature"
```

### 6. Push до GitHub

```bash
git push origin feature/my-new-feature
```

### 7. Створення Pull Request

1. Перейдіть на ваш fork на GitHub
2. Натисніть **"New Pull Request"**
3. Заповніть шаблон PR
4. Дочекайтесь review

---

## 🌿 Структура гілок

### main
- Стабільна версія
- Тільки через Pull Request
- Захищена від прямих commit

### develop
- Розробка нових функцій
- Інтеграція feature гілок

### feature/*
- Нові функції
- Створюються з `develop`
- Приклад: `feature/add-calendar`

### bugfix/*
- Виправлення багів
- Створюються з `develop` або `main`
- Приклад: `bugfix/fix-crash-on-login`

### hotfix/*
- Критичні виправлення
- Створюються з `main`
- Зливаються в `main` та `develop`

---

## 📝 Стандарти коду

### Kotlin Style Guide

Дотримуйтесь [офіційного Kotlin style guide](https://kotlinlang.org/docs/coding-conventions.html)

```kotlin
// ✅ Добре
class TasksAdapter(
    private val tasks: List<Task>,
    private val onTaskClick: (Task) -> Unit
) : RecyclerView.Adapter<TasksAdapter.ViewHolder>() {
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = tasks[position]
        holder.bind(task)
    }
}

// ❌ Погано
class TasksAdapter(private val tasks:List<Task>,private val onTaskClick:(Task)->Unit):RecyclerView.Adapter<TasksAdapter.ViewHolder>(){
    override fun onBindViewHolder(holder:ViewHolder,position:Int){
        val task=tasks[position]
        holder.bind(task)}
}
```

### Іменування

- **Classes/Objects**: `PascalCase`
- **Functions/Variables**: `camelCase`
- **Constants**: `UPPER_SNAKE_CASE`
- **Resources**: `snake_case`

```kotlin
// Classes
class TasksActivity
data class StudentProfile

// Functions
fun loadTasks()
private fun showDialog()

// Variables
val userName: String
var isCompleted: Boolean

// Constants
const val MAX_TASKS = 100
const val API_BASE_URL = "https://api.example.com"

// Resources
activity_tasks.xml
ic_launcher_foreground.xml
```

### Коментарі

```kotlin
/**
 * Завантажує список завдань з Firestore
 * 
 * @param userId ID користувача
 * @return List<Task> список завдань
 */
fun loadTasks(userId: String): List<Task> {
    // Виконуємо запит до Firestore
    val tasks = db.collection("tasks")
        .whereEqualTo("userId", userId)
        .get()
    
    return tasks.documents.mapNotNull { Task.fromFirestore(it) }
}
```

### XML Layout

```xml
<!-- ✅ Добре -->
<TextView
    android:id="@+id/tvTaskTitle"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:text="@string/task_title"
    android:textSize="18sp"
    android:textStyle="bold"
    android:textColor="@color/text_primary" />

<!-- ❌ Погано -->
<TextView android:id="@+id/tvTaskTitle" android:layout_width="match_parent" android:layout_height="wrap_content" android:text="Task Title" android:textSize="18sp" android:textStyle="bold" android:textColor="#212121"/>
```

---

## 💬 Commit повідомлення

Використовуйте [Conventional Commits](https://www.conventionalcommits.org/)

### Формат

```
<type>(<scope>): <subject>

<body>

<footer>
```

### Types

- `feat` - нова функція
- `fix` - виправлення бага
- `docs` - зміни в документації
- `style` - форматування коду
- `refactor` - рефакторинг
- `test` - додавання тестів
- `chore` - оновлення build або dependencies

### Приклади

```bash
# Нова функція
git commit -m "feat(tasks): add real-time sync with Firestore"

# Виправлення бага
git commit -m "fix(auth): resolve crash on empty email field"

# Документація
git commit -m "docs(readme): add Firebase setup instructions"

# Рефакторинг
git commit -m "refactor(profile): extract data loading to separate function"

# З body
git commit -m "feat(tasks): add task deletion

- Add delete button to task items
- Implement confirmation dialog
- Update Firestore on deletion"
```

---

## 🔀 Pull Request процес

### Перед створенням PR

- [ ] Код компілюється без помилок
- [ ] Всі тести проходять
- [ ] Код відформатовано
- [ ] Додано коментарі
- [ ] Оновлено документацію
- [ ] Немає конфліктів з `main`

### Шаблон Pull Request

```markdown
## Опис
[Опишіть ваші зміни]

## Тип зміни
- [ ] Bug fix (non-breaking change)
- [ ] New feature (non-breaking change)
- [ ] Breaking change
- [ ] Documentation update

## Як тестувати?
1. Запустити додаток
2. Перейти до ...
3. Перевірити що ...

## Checklist
- [ ] Мій код дотримується стилю проєкту
- [ ] Я провів self-review коду
- [ ] Я додав коментарі
- [ ] Я оновив документацію
- [ ] Мої зміни не генерують нові warnings
- [ ] Я додав тести
- [ ] Всі тести проходять

## Скріншоти (якщо є)
[Додайте скріншоти]
```

### Review процес

1. Автоматична перевірка CI/CD (якщо налаштовано)
2. Code review від maintainer
3. Обговорення та зміни (якщо потрібно)
4. Затвердження та злиття

---

## 🧪 Тестування

### Unit Tests

```kotlin
class TaskTest {
    @Test
    fun `task isOverdue returns true for past date`() {
        val task = Task(
            id = "1",
            title = "Test",
            deadline = "01.01.2020"
        )
        assertTrue(task.isOverdue())
    }
}
```

### Запуск тестів

```bash
# Unit tests
./gradlew test

# Instrumentation tests
./gradlew connectedAndroidTest
```

---

## 📚 Додаткові ресурси

- [Kotlin Documentation](https://kotlinlang.org/docs/home.html)
- [Android Developers Guide](https://developer.android.com/guide)
- [Firebase Documentation](https://firebase.google.com/docs)
- [Material Design Guidelines](https://material.io/design)

---

## ❓ Питання?

Якщо у вас виникли питання:
- Створіть [Issue](https://github.com/YOUR_USERNAME/StudentPortal/issues)
- Напишіть на email: dmytro.vasylets@nure.ua

---

## 🎉 Дякуємо!

Кожен внесок цінний, незалежно від розміру. Дякуємо за допомогу в покращенні Student Portal!

<div align="center">
  
**Happy Coding! 💻**

</div>
