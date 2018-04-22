<#include "masters/mainMenu.ftl">
<@menu title="Main page">
<div class="row">
    <div class="btn-group" role="group" aria-label="...">
        <button onclick="window.location.href='/admin/test/add_question?test_id=${test.id}'" type="button"
                class="btn btn-default">Добавить вопросы
        </button>
    </div>
</div>

<div class="row">
    <div class="panel panel-default">
        <#list test.questions as question>

            <div class="panel-heading">${question.question}</div>
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>#</th>
                    <th>Ответ</th>
                </tr>
                </thead>
                <tbody>
            <#assign x = 1>
                <#list question.answers as answer>
                <#--TODO пофиксить жирный текст-->
                <tr>
                    <th scope="row">${x}</th>
                    <th style="font-weight:300">${answer.answer}</th>
                </tr>
                    <#assign x++>
                </#list>
                </tbody>
            </table>
        </#list>
    </div>
</div>
</@menu>

