$(document).ready(function() {
    $('#country').change(function(event) {
        var $country=$("select#country").val();
        $.get('JsonServlet',{country_id:$country},function(responseJson) {
            var $select = $('#city');
            $select.find('option').remove();
            $.each(responseJson, function(key, value) {
                $('<option>').val(key).text(value).appendTo($select);
            });
        });
    });
});