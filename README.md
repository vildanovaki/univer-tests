# :feet: Проект с автотестами для сайта pet-yes.com 

![alt "PetYes"](./images/PetYes.png "PetYes")

## :gear: Стек технологий:
| IDEA | Java | Selenide | Selenoid | Allure Report | Gradle | Junit5 | GitHub | Jenkins | Allure TO | Jira |
|:--------:|:-------------:|:---------:|:-------:|:----:|:------:|:----:|:----:|:------:|:------:|:--------:|
| <img src="images/Intelij_IDEA.svg" width="40" height="40"> | <img src="images/JAVA.svg" width="40" height="40"> | <img src="images/Selenide.svg" width="40" height="40"> | <img src="images/Selenoid.svg" width="40" height="40"> | <img src="images/Allure_Report.svg" width="40" height="40"> | <img src="images/Gradle.svg" width="40" height="40"> | <img src="images/Junit5.svg" width="40" height="40"> | <img src="images/GitHub.svg" width="40" height="40"> | <img src="images/Jenkins.svg" width="40" height="40"> | <img src="images/Allure_TO.svg" width="40" height="40"> | <img src="images/Jira.svg" width="40" height="40"> |
___

## :pushpin: В качестве CI системы использован Jenkins

![alt "Jenkins"](./images/Jenkins.png "Jenkins")

## :arrow_forward: Запуск тестов

```

Для запуска тестов необходимо выполнить следующую команду:

```bash
gradle clean test
```

Если файл local.properties не заполнен, то для запуска тестов необходимо выполнить следующую команду:

```bash
gradle clean test 
-Dbrowser=${BROWSER}
-Dversion=${VERSION}
-Dsize=${SIZE}

```
- в параметре Dbrowser - указываем браузер, в котором будут выполняться тесты
- в параметре Dversion - указываем версию браузера
- в параметре Dsize - указываем размер окна браузера

## :bar_chart: Генерация отчета происходит в Allure Report

Для генерации отчета необходимо выполнить следующую команду:

```bash
allure serve build/allure-results
```

![alt "Allure Report"](./images/Allure_report1.png "Allure Report")

К каждому тесту прикладываются:
- Скриншоты
- Исходники страницы
- Логи браузера
- Видео

![alt "Allure Report"](./images/Allure_report2.png "Allure Report")

## :bar_chart: Результаты прохождения тестов записываются в Allure TestOps

![alt "Allure TestOps"](./images/Allure_TO.png "Allure TestOps")

## :pushpin: Проект интегрирован с [Jira](https://jira.autotests.cloud/browse/AUTO-488)

![alt "Jira"](./images/Jira.png "Jira")

## :heavy_check_mark: Уведомления о прохождении тестов отправляются в Telegram

![alt "Telegramm_notification"](./images/Telegramm_notification.png "Telegramm_notification") 

## :movie_camera: Видео с прохождением теста "Создание питомца"

![alt "CreatePetVideo"](./images/CreatePetVideo.gif "CreatePetVideo") 
