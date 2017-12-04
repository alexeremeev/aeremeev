$(document).ready(function () {

    $("#create-btn").click(function () {

        var name = $("#name");
        var password = $("#password");
        var role = $("#role");
        var address = $("#address");
        var musicType = $("#musicType");

        if (name != '') {
            $.ajax({
                url: "create",
                method: "post",
                data: {
                    'name': name.val(),
                    'password': password.val(),
                    'role': role.val(),
                    'address': address.val(),
                    'musicType': musicType.val()},
                complete: function (data) {
                    var result = JSON.parse(data.responseText);
                    if (Boolean(result.success)) {
                        alert("User successfully created.");
                        clearFields();
                    }
                    else {
                        alert("Something went wrong, during user creation process, check input data!");
                    }
                }
            });
        } else {
            alert("Please check input data!");
        }
    });

    function clearFields() {
        $('input').val('');
    };

    $("#addr-btn").click(function () {

        var address = $("#Address_field");
        if (address != '') {
            $.ajax({
                url: "json",
                method: "get",
                datatype: 'json',
                data: {
                    'address': address.val(),
                    'music': '',
                    'role': '',
                    'roleAll': ''
                },
                complete: function (data) {
                    var result = JSON.parse(data.responseText);
                    if(result.user != ''){
                        var user =  JSON.parse(result.user);
                        var insertTable = "";
                        for (var i = 0; i != user.length; ++i) {
                            insertTable += "<tr>";
                            insertTable += "<td>"+user[i].name+"</td>";
                            insertTable += "<td>"+user[i].address+"</td>";
                            insertTable += "<td>"+user[i].musictypes+"</td>";
                            insertTable += "<td>"+user[i].role+"</td>";
                            insertTable += "</tr>";
                        }
                        var filledTable = document.getElementById("insert");
                        filledTable.innerHTML = insertTable;

                    }
                }
            });
        }
    });

    $("#role-btn").click(function () {

        var role = $("#Role_field");
        if (role != '') {
            $.ajax({
                url: "json",
                method: "get",
                datatype: 'json',
                data: {
                    'address': '',
                    'music': '',
                    'role': role.val(),
                    'roleAll': ''
                },
                complete: function (data) {
                    var result = JSON.parse(data.responseText);
                    if(result.user != ''){
                        var user =  JSON.parse(result.user);
                        var insertTable = "";
                        for (var i = 0; i != user.length; ++i) {
                            insertTable += "<tr>";
                            insertTable += "<td>"+user[i].name+"</td>";
                            insertTable += "<td>"+user[i].address+"</td>";
                            insertTable += "<td>"+user[i].musictypes+"</td>";
                            insertTable += "<td>"+user[i].role+"</td>";
                            insertTable += "</tr>";
                        }
                        var filledTable = document.getElementById("insert");
                        filledTable.innerHTML = insertTable;
                        table.show();
                    }
                }
            });
        }
    });

    $("#musictype-btn").click(function () {

        var music = $("#Musictype_field");
        if (music != '') {
            $.ajax({
                url: "json",
                method: "get",
                datatype: 'json',
                data: {
                    'address': '',
                    'music': music.val(),
                    'role': '',
                    'roleAll': ''
                },
                complete: function (data) {
                    var result = JSON.parse(data.responseText);
                    if(result.user != ''){
                        var user =  JSON.parse(result.user);
                        var insertTable = "";
                        for (var i = 0; i != user.length; ++i) {
                            insertTable += "<tr>";
                            insertTable += "<td>"+user[i].name+"</td>";
                            insertTable += "<td>"+user[i].address+"</td>";
                            insertTable += "<td>"+user[i].musictypes+"</td>";
                            insertTable += "<td>"+user[i].role+"</td>";
                            insertTable += "</tr>";
                        }
                        var filledTable = document.getElementById("insert");
                        filledTable.innerHTML = insertTable;
                        table.show();
                    }
                }
            });
        }
    });

    $("#role-all-btn").click(function () {

        var role = $("#Role_field");
        if (role != '') {
            $.ajax({
                url: "json",
                method: "get",
                datatype: 'json',
                data: {
                    'address': '',
                    'music': '',
                    'role': '',
                    'roleAll': 'role'
                },
                complete: function (data) {
                    var result = JSON.parse(data.responseText);
                    if(result.user != ''){
                        var user =  JSON.parse(result.user);
                        var insertTable = "";
                        for (var i = 0; i != user.length; ++i) {
                            insertTable += "<tr>";
                            insertTable += "<td>"+user[i].name+"</td>";
                            insertTable += "<td>"+user[i].address+"</td>";
                            insertTable += "<td>"+user[i].musictypes+"</td>";
                            insertTable += "<td>"+user[i].role+"</td>";
                            insertTable += "</tr>";
                        }
                        var filledTable = document.getElementById("insert");
                        filledTable.innerHTML = insertTable;
                        table.show();
                    }
                }
            });
        }
    });

});
