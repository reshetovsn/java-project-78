### Tests and linter status:
[![Actions Status](https://github.com/reshetovsn/java-project-78/workflows/hexlet-check/badge.svg)](https://github.com/reshetovsn/java-project-78/actions)
[![Validation-check](https://github.com/reshetovsn/java-project-78/actions/workflows/main.yml/badge.svg)](https://github.com/reshetovsn/java-project-78/actions/workflows/main.yml)
[![Maintainability](https://api.codeclimate.com/v1/badges/c21d3d00c23e13695e11/maintainability)](https://codeclimate.com/github/reshetovsn/java-project-78/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/c21d3d00c23e13695e11/test_coverage)](https://codeclimate.com/github/reshetovsn/java-project-78/test_coverage)
## Описание
Валидатор данных – библиотека, с помощью которой можно проверять корректность данных. В данном валидаторе реализована возможность проверки strings, ints и maps.

### Валидация строк:
* required() - ограничение, которое не позволяет использовать null или пустую строку в качестве значения  
* minLength() - добавляет ограничение минимальной длины для строки. Строка должна быть равна или длиннее указанного числа  
* contains() - добавляет ограничение по содержимому строки. Строка должна содержать определённую подстроку  

```Validator v = new Validator();

StringSchema schema = v.string();

// Пока на вызван метод required(), null и пустая строка считаются валидным
schema.isValid(""); // true
schema.isValid(null); // true

schema.required();

schema.isValid(null); // false
schema.isValid(""); // false
schema.isValid(5); // false
schema.isValid("what does the fox say"); // true
schema.isValid("hexlet"); // true

schema.contains("wh").isValid("what does the fox say"); // true
schema.contains("what").isValid("what does the fox say"); // true
schema.contains("whatthe").isValid("what does the fox say"); // false

schema.isValid("what does the fox say"); // false
// Здесь уже false, так как добавлена еще одна проверка contains("whatthe")
```
### Валидация чисел:
* required() - добавляет в схему ограничение, которое не позволяет использовать null в качестве значения  
* positive() - добавляет ограничение минимальной длины для строки. Строка должна быть равна или длиннее указанного числа  
* contains() - добавляет ограничение по содержимому строки. Строка должна содержать определённую подстроку  

```Validator v = new Validator();

NumberSchema schema = v.number();

// Пока не вызван метод required(), null считается валидным
schema.isValid(null); // true
schema.positive().isValid(null); // true

schema.required();

schema.isValid(null); // false
schema.isValid("5"); // false
schema.isValid(10) // true

// Потому что ранее мы вызвали метод positive()
schema.isValid(-10); // false
//  Ноль — не положительное число
schema.isValid(0); // false

schema.range(5, 10);

schema.isValid(5); // true
schema.isValid(10); // true
schema.isValid(4); // false
schema.isValid(11); // false
```
### Валидация объектов типа Map:
* required() - добавляет в схему ограничение, которое не позволяет использовать null в качестве значения  
* positive() - добавляет ограничение минимальной длины для строки. Строка должна быть равна или длиннее указанного числа  
* contains() - добавляет ограничение по содержимому строки. Строка должна содержать определённую подстроку  

```Validator v = new Validator();

MapSchema schema = v.map();

schema.isValid(null); // true

schema.required();

schema.isValid(null) // false
schema.isValid(new HashMap()); // true
Map<String, String> data = new HashMap<>();
data.put("key1", "value1");
schema.isValid(data); // true

schema.sizeof(2);

schema.isValid(data);  // false
data.put("key2", "value2");
schema.isValid(data); // true
```
### Вложенная валидация:
* shape() - используется для определения свойств объекта Map и создания схемы для валидации их значений. 
Каждому свойству привязывается набор ограничений, что позволяет более точно контролировать данные  

```Validator v = new Validator();

MapSchema schema = v.map();

// shape позволяет описывать валидацию для значений объекта Map по ключам
Map<String, BaseSchema> schemas = new HashMap<>();

// Определяем схемы валидации для значений свойств "name" и "age"
// Имя должно быть строкой, обязательно для заполнения
schemas.put("name", v.string().required());
// Возраст должен быть положительным числом
schemas.put("age", v.number().positive());
schema.shape(schemas);

// Проверяем объекты
Map<String, Object> human1 = new HashMap<>();
human1.put("name", "Kolya");
human1.put("age", 100);
schema.isValid(human1); // true

Map<String, Object> human2 = new HashMap<>();
human2.put("name", "Maya");
human2.put("age", null);
schema.isValid(human2); // true

Map<String, Object> human3 = new HashMap<>();
human3.put("name", "");
human3.put("age", null);
schema.isValid(human3); // false

Map<String, Object> human4 = new HashMap<>();
human4.put("name", "Valya");
human4.put("age", -5);
schema.isValid(human4); // false
```
