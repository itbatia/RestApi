<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

<head>
    <title>End points!</title>
    <meta http-equiv="content-type" content="text/html" charset="UTF-8">
    <link href="../css/style_main.css" rel="stylesheet" type="text/css">
</head>

<body>

<div class="main">

    <div class="title">
        <h1>RestApi</h1>
    </div>

    <table>

        <!--Header-->
        <tr>
            <td class="method_column" style="text-align: center; background-color: antiquewhite; height: 30px">Method</td>
            <td class="url_column" style="text-align: center; font-weight: bold; background-color: antiquewhite">URL</td>
            <td class="body_column" style="text-align: center; font-weight: bold; background-color: antiquewhite">Body — JSON. For example:</td>
        </tr>

        <!--Files-->
        <tr>
            <td class="method_column_post">POST</td>
            <td class="url_column">https://restapiapply.herokuapp.com/v1/files</td>
            <td class="body_column">{ "name":"FileName", "content":"Some content" }</td>
        </tr>
        <tr>
            <td class="method_column_get">GET</td>
            <td class="url_column">
                https://restapiapply-2.herokuapp.com/v1/files<br>
                https://restapiapply-2.herokuapp.com/v1/files/{file_id}
            </td>
            <td class="body_column">-</td>
        </tr>
        <tr>
            <td class="method_column_put">PUT</td>
            <td class="url_column">https://restapiapply-2.herokuapp.com/v1/files/{file_id}</td>
            <td class="body_column">{ "name":"NewName", "content":"This is new some content!" }</td>
        </tr>
        <tr>
            <td class="method_column_delete">DELETE</td>
            <td class="url_column">https://restapiapply-2.herokuapp.com/v1/files/{file_id}</td>
            <td class="body_column">-</td>
        </tr>

        <!--Users-->
        <tr>
            <td class="method_column_post">POST</td>
            <td class="url_column">https://restapiapply-2.herokuapp.com/v1/users</td>
            <td class="body_column">{ "name":"Anna", "events":null }</td>
        </tr>
        <tr>
            <td class="method_column_get">GET</td>
            <td class="url_column">
                https://restapiapply-2.herokuapp.com/v1/users<br>
                https://restapiapply-2.herokuapp.com/v1/users/{user_id}
            </td>
            <td class="body_column">-</td>
        </tr>
        <tr>
            <td class="method_column_put">PUT</td>
            <td class="url_column">https://restapiapply-2.herokuapp.com/v1/users/{user_id}</td>
            <td class="body_column">
                {"name":"Valera", "events": [ { "id":1, "activity":"CREATION", "dateEvent":"Apr 27, 2022, 12:00:00 AM",
                "file": { "id":1, "name":"FileName", "content": "Some content" }, "userId":1 }]}
            </td>
        </tr>
        <tr>
            <td class="method_column_delete">DELETE</td>
            <td class="url_column">https://restapiapply-2.herokuapp.com/v1/users/{user_id}</td>
            <td class="body_column">-</td>
        </tr>

        <!--Events-->
        <tr>
            <td class="method_column_post">POST</td>
            <td class="url_column">https://restapiapply-2.herokuapp.com/v1/events</td>
            <td class="body_column">
                { "activity":"CREATION", "dateEvent":"Apr 27, 2022, 12:00:00 AM", "file": {
                "id":1, "name":"FileName", "content":"Some content" }, "userId":"1" }</td>
        </tr>
        <tr>
            <td class="method_column_get">GET</td>
            <td class="url_column">
                https://restapiapply-2.herokuapp.com/v1/events<br>
                https://restapiapply-2.herokuapp.com/v1/events/{event_id}
            </td>
            <td class="body_column">-</td>
        </tr>
        <tr>
            <td class="method_column_put">PUT</td>
            <td class="url_column">https://restapiapply-2.herokuapp.com/v1/events/{event_id}</td>
            <td class="body_column">
                { "activity":"UPDATE", "dateEvent":"Apr 27, 2022, 12:00:00 AM", "file": {
                "id": 1, "name": "FileName", "content": "Some content" }, "userId": 1 }
            </td>
        </tr>
        <tr>
            <td class="method_column_delete">DELETE</td>
            <td class="url_column">https://restapiapply-2.herokuapp.com/v1/events/{event_id}</td>
            <td class="body_column">-</td>
        </tr>

    </table>

    <div class="footer">Репозиторий с исходным кодом:
        <a href="https://github.com/itbatia/RestApi" target="_blank">https://github.com/itbatia/RestApi</a>
    </div>

</body>
</html>


