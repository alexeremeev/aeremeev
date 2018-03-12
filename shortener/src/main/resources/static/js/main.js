var register = false;
var user = "anonymousUser";
var CONTEXT_PATH = document.getElementsByTagName('meta')[1].content;

$(document).ready(function () {

    validateSession();
    shiftMenu();
    viewOrders();

    $("#auth-btn").click(function () {
        var data = 'username=' + $('#username').val() + '&password=' + $('#password').val();
        $.ajax({
            url: "login",
            type: "post",
            timeout: 1000,
            data: data})
            .done(function (data, textStatus) {
                console.log('status', textStatus);
                register = true;
                user = $('#username').val();
                shiftMenu();
            }).fail(function (jqXHR, textStatus, errorThrown) {
            console.log('data', errorThrown);
            console.log('status', textStatus);
            console.log('xhr', jqXHR);
            alert('Fail!');
        });

    });

    $("#add-user-btn").click(function () {

        var username = {
            'username' : $("#username").val()
        };

            $.ajax({
                url: "account",
                method: "post",
                data: {
                    'json': JSON.stringify(username)
                },
                complete: function (data) {
                    var result = JSON.parse(data.responseText);
                    register = Boolean(result.success);
                    var message = result.description;
                    if (register) {
                        alert(message)
                    }
                    else {
                        alert(message);
                    }
                }
            });

    });


});

function validateSession() {
    $.ajax({
        url: "login",
        method: "get",
        complete: function (data) {
            var result = JSON.parse(data.responseText);
            register = Boolean(result.success);
            user = result.username;
            shiftMenu();
        }
    });
}

function shiftMenu() {
    if (register) {
        $("#authorisation").hide();
        $("#info_success_auth").show();
        // $("#move-order-page").show();
        viewOrders();
    }
}

function viewOrders() {
    var url = "statistic/" + user;
    $.ajax({
        url: url,
        method: "get",
        complete: function (data) {
            var result = JSON.parse(data.responseText);
            if (result != '[]') {
                var optional = "";
                for (var i = 0; i != result.length; ++i) {
                    optional += "<tr>";
                    optional += "<td><a href='"+result[i].original+"'>"+result[i].original+"</a></td>";
                    optional += "<td><a href='"+result[i].shortened+"'>"+result[i].shortened+"</a></td>";
                    optional += "<td>"+result[i].counter+"</td>";
                    optional += "</tr>";
                }
                $("#table-body").html(optional);
            }
        }
    });
}

function addUrl() {
    var url = $("#url").val();
    var redirectType = $("#redirectType").val();

    var dto = {
        'url' : url,
        'redirectType' : redirectType
    };

    $.ajax({
        url: "register",
        method: "post",
        data: {
            'request' : JSON.stringify(dto)
        },
        complete: function (data) {
            var result = JSON.parse(data.responseText);
            var show = result.shortUrl;
            alert("Url shortened to:" + show);
        }
    });
}
