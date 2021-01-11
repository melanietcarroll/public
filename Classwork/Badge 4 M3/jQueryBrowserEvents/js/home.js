$(document).ready(function () {
    $("#mainInfoDiv").html();
    $('#akronInfoDiv').hide();
    $('#minneapolisInfoDiv').hide();
    $('#louisvilleInfoDiv').hide();
    $('#akronButton').on('click', function() {
       $('#akronInfoDiv').toggle('slow'); 
       $('#akronWeather').hide();
       $('#minneapolisInfoDiv').hide();
        $('#louisvilleInfoDiv').hide();
    });
    $('#minneapolisButton').on('click', function() {
       $('#minneapolisInfoDiv').toggle('slow'); 
       $('#minneapolisWeather').hide();
        $('#louisvilleInfoDiv').hide();
        $('#akronInfoDiv').hide();
    });
    $('#louisvilleButton').on('click', function() {
       $('#louisvilleInfoDiv').toggle('slow'); 
       $('#louisvilleWeather').hide();
        $('#minneapolisInfoDiv').hide();
        $('#akronInfoDiv').hide();
    });
    $('#akronWeatherButton').on('click', function() {
       $('#akronWeather').toggle('slow'); 
    });
    $('#minneapolisWeatherButton').on('click', function() {
       $('#minneapolisWeather').toggle('slow'); 
    });
    $('#louisvilleWeatherButton').on('click', function() {
       $('#louisvilleWeather').toggle('slow'); 
    });
    $("td").hover(
    // in callback
    function () {
        $(this).css("background-color", "WhiteSmoke");
    },
    // out callback
    function () {
        $(this).css("background-color", "");
    }
);
});