<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

<head>
    <title>Welcome to RestApi!</title>
    <meta http-equiv="content-type" content="text/html" charset="UTF-8">
    <link href="css/style_index.css" rel="stylesheet" type="text/css">
</head>

<body>

<div class="main">

    <div class="title">
        <h1>RestApi</h1>
        Это приложение, которое взаимодействует с файловым хранилищем и предоставляет возможность
        получать доступ к файлам и истории загрузок.
    </div>

    <div class="entity">
        <h3>Взаимодействие происходит со следующими сущностями:</h3>
        <ul><i>
            <li>User (id, name, List &lt;Event&gt events);</li>
            <li>File (id, name, content);</li>
            <li>Event (id, Activity activity, Date dateEvent, File file, userId)</li>
            <li>Activity (enum CREATION, READING, UPDATE, DELETION).</li>
        </i></ul>
        <hr>
    </div>

    <div class="requirements">
        <h3>Приложение соответствует требованиям:</h3>
        <ul>
            <li>Все CRUD операции для каждой из сущностей</li>
            <li>Соответствие шаблону <b>MVC</b></li>
            <li>Для сборки проекта - <b>Maven</b></li>
            <li>Для взаимодействия с БД – <b>Hibernate</b></li>
            <li>Для конфигурирования Hibernate - аннотации</li>
            <li>Инициализация БД реализована с помощью <b>Flyway</b></li>
            <li>Репозиторий имеет бейдж сборки <b>travis</b></li>
            <li>Рабочее приложение развернуто на <b>heroku.com</b></li>
        </ul>
    </div>

    <div class="postman">
        Взаимодействие с пользователем реализовано с помощью 2-х программ на выбор:
        <a href="https://www.getpostman.com/" target="_blank">Postman</a> или
        <a href="https://insomnia.rest/download" target="_blank">Insomnia</a><br>
        <hr>
    </div>

    <div class="buttonDiv">
        <h3>
            <form name="enter" action="pages/main.jsp" method="get">
                Endpoints для тестиропания приложения RestApi находятся
                <button class="button" type="submit" name="enter">Здесь</button>
            </form>
        </h3>
    </div>

</div>

<div class="footer">Репозиторий с исходным кодом:
    <a href="https://github.com/itbatia/RestApi" target="_blank">https://github.com/itbatia/RestApi</a>
</div>

</body>
</html>