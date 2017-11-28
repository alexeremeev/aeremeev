$(document).ready(function() {
    $('#country').change(function(event) {
        var $country=$("select#country").val();
        $.get('ActionServlet',{countryname:$country},function(responseJson) {
            var $select = $('#city');
            $select.find('option').remove();
            $.each(responseJson, function(key, value) {
                $('<option>').val(key).text(value).appendTo($select);
            });
        });
    });
});