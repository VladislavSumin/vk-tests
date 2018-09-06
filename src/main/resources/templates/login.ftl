<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Авторизация</title>

    <#include "masters/bootstrap.ftl">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-default" style="margin-top:45px">
                <div class="panel-heading">
                    <h3 class="panel-title">Авторизация</h3>
                </div>
                <div class="panel-body">
                <#if logout>
                    <div class="alert alert-info" role="alert">Вы вышли с сайта</div>
                </#if>
                <#if error>
                    <div class="alert alert-danger" role="alert">Не верное имя пользователя или пароль!</div>
                </#if>

                    <form method="post">
                        <input name="${_csrf.parameterName}" value="${_csrf.token}" type="hidden">
                        <div class="form-group">
                        <#--<label for="username">Username</label>-->
                            <input type="text" class="form-control" id="username" placeholder="Имя пользователя"
                                   name="username">
                        </div>
                        <div class="form-group">
                        <#--<label for="password">Password</label>-->
                            <input type="password" class="form-control" id="password" placeholder="Пароль"
                                   name="password">
                        </div>
                        <button type="submit" class="btn btn-default">Войти</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>