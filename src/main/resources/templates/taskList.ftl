<#include "masters/mainMenu.ftl">
<@menu title="Main page">

<div class="row">
    <div class="panel panel-default">
        <div class="panel-heading">Список заданий</div>

        <table class="table">
            <thead>
            <tr>
                <th>#</th>
                <th>Название теста</th>
                <th>Время на выполнение</th>
                <th>Время окончания</th>
            </tr>
            </thead>
            <tbody>
            <#assign x = 1>
            <#list tasks as task>
            <#--TODO пофиксить жирный текст-->
            <tr>
                <th scope="row">${x}</th>
                <th style="font-weight:300">${task.test.name}</th>
                <th style="font-weight:300">${task.test.timeLimit}</th>
                <th style="font-weight:300">${task.endTime}</th>
                <th style="font-weight:300">
                    <form method="post">
                        <input name="${_csrf.parameterName}" value="${_csrf.token}" type="hidden">
                        <input type="hidden" name="task_id" value="${task.id}">
                        <input type="submit" name="submit" value="Начать">
                    </form>
                </th>
            </tr>
                <#assign x++>
            </#list>
            </tbody>
        </table>
    </div>
</div>
</@menu>

