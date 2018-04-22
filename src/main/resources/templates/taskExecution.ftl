<#include "masters/mainMenu.ftl">
<@menu title="Main page">
<div class="panel panel-default" style="margin-top:45px">
    <div class="panel-heading">
        <h2 class="panel-title">Верных ответов: ${rightAnswers} (${answeredQuestion} / ${countQuestion}) Времени
            осталось: <label id="demo"></label></h2>
    </div>
</div>
<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">${question.question}</h3>
    </div>
    <div class="panel-body">

    <#if error??>
    <div class="alert alert-danger" role="alert">${error}</div>
    </#if>

        <form method="post">
            <input name="${_csrf.parameterName}" value="${_csrf.token}" type="hidden">
            <input name="question_id" value="${question.id}" type="hidden">
            <#assign x = 1>
            <#list question.answers as answer>
            <div class="form-group">
            <#--нужно править-->
                <input type="hidden" name="answer_${x}_id" value="${answer.id}">
                <label>
                    <input type="checkbox" name="answer_${x}_is_right">
                    ${answer.answer}
                </label>
            </div>
                <#assign x++>
            </#list>
            <button type="submit" class="btn btn-default">Ответить</button>
        </form>
    </div>
</div>

<script>
    var countDownDate = new Date("${time?datetime?string("yyyy-MM-dd'T'HH:mm:ss")}").getTime();

    var x = setInterval(function () {
        var now = new Date().getTime();
        var distance = countDownDate - now;

        var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
        var seconds = Math.floor((distance % (1000 * 60)) / 1000);

        document.getElementById("demo").innerHTML = minutes + "m " + seconds + "s ";

        if (distance < 0) {
            clearInterval(x);
            document.getElementById("demo").innerHTML = "EXPIRED";
        }
    }, 1000);
</script>
</@menu>

