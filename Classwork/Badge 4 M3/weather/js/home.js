$(document).ready(function () {
    getWeatherByZip();
    getFiveDayWeatherByZip();

});
function getWeatherByZip() {

    var title = $('#title');
    var image = $('#currentWeatherImage');
    var currentConditions = $('#currentWeather');
    var currentTemp = $('#currentZipTemperature');
//    var currentHum = $('#currentHumidity');
    $.ajax({
        type: 'GET',
        url: 'http://api.openweathermap.org/data/2.5/weather?zip=55016,us&units=imperial&appid=deee34191a434aaafb4e697664efb1ba',
        success: function (data, status) {

            var name = data.name;
            var cityTitle = '<h2>' + 'Current conditions in ' + name + '</h2>';
            title.append(cityTitle);

            var currentWeatherPic = data.weather[0].icon;
            var currentPicDiv = '<img src="http://openweathermap.org/img/w/' + currentWeatherPic + '.png" class="img-fluid">';
            image.append(currentPicDiv);

            var currentWeath = data.weather[0].main;
            var currentWeatherStatus = '<p>' + currentWeath + '</p>';
            currentConditions.append(currentWeatherStatus);

            var currentAreaTemperature = data.main[0].temp;
            var currentTempDiv = '<p>' + 'Current Temperature: ' + '</p>';
            currentTemp.append(currentTempDiv);

            var humidity = data.main[0].humidity;
            var humidityDiv = '<p>Humidity</p>';
//            currentHum.append(humidityDiv);
            $('#currentHumidity').append('<p>A new paragraph of text...</p>');
//           $('#currentHumidity').html( "<span class='red'>Hello <b>Again</b></span>" );
        },

        error: function () {
            $('#errorMessages')
                    .append($('<li>')
                            .attr({class: 'list-group-item list-group-item-danger'})
                            .text('Error calling web service. Please try again later.'));
        }

    });
}
function getFiveDayWeatherByZip() {


    $.ajax({
        type: 'GET',
        url: 'http://api.openweathermap.org/data/2.5/forecast?zip=55016,us&units=imperial&appid=deee34191a434aaafb4e697664efb1ba',
        success: function (weatherArray) {
            $.each(weatherArray, function (index, item) {
            var day = item.list[8].dt_txt;
//                    var releaseYear = dvd.releaseYear;
//                    var director = dvd.director;
//                    var rating = dvd.rating;
//                    var notes = dvd.notes;
//                    var dvdId = dvd.id;
//                    var name = data.name;
                    var date = '<span>' + day  + '</span>';
                    title.append(cityTitle);
                    var currentWeatherPic = data.weather[0].icon;
                    var currentPicDiv = '<img src="http://openweathermap.org/img/w/' + currentWeatherPic + '.png" class="img-fluid">';
                    image.append(currentPicDiv);
                    var currentWeath = data.weather[0].main;
                    var currentWeatherStatus = '<p>' + currentWeath + '</p>';
                    currentConditions.append(currentWeatherStatus);
                    var currentAreaTemperature = data.main[0].temp;
                    var currentTempDiv = '<p>' + 'Current Temperature: ' + '</p>';
                    currentTemp.append(currentTempDiv);
                    var humidity = data.main[0].humidity;
                    var humidityDiv = '<p>Humidity</p>';
//            currentHum.append(humidityDiv);
                    $('#currentHumidity').append('<p>A new paragraph of text...</p>');
//           $('#currentHumidity').html( "<span class='red'>Hello <b>Again</b></span>" );
            },
                    error: function () {
                    $('#errorMessages')
                            .append($('<li>')
                                    .attr({class: 'list-group-item list-group-item-danger'})
                                    .text('Error calling web service. Please try again later.'));
                }

    });
}

