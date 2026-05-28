# GogaMagazin (Java)

Учебная Java-копия проекта `GogaMagazin(C++)` с максимально похожей структурой/логикой (консоль, роли, склад, продажа, файлы `users_db.txt` и `Storage.txt`).

## Запуск (без Maven)

Так как у вас команда `mvn` не найдена, можно собрать/запустить через `javac/java`.

Сборка (UTF-8):

```powershell
cd "c:\Users\П - 11\Documents\Deev\magaz\magaz\GogaMagazin(Java)"
New-Item -ItemType Directory -Force -Path target | Out-Null
javac -encoding UTF-8 -d target src\main\java\*.java
```

Запуск:

```powershell
java -cp target Main
```