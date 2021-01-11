$(document).ready(function () {
    $('H1').addClass('text-center');
    $('H2').addClass('text-center');
    $('.myBannerHeading').val('.page-header');
    $('#yellowHeading').text("Yellow Team");
    $('#orangeHeading').css('background-color', 'orange');
    $('#blueHeading').css('background-color', 'blue');
    $('#redHeading').css('background-color', 'red');
    $('#redHeading').css('background-color', 'red');
    $('#yellowHeading').css('background-color', 'yellow');
    $('#yellowTeamList').append('<li>Joseph Banks</li>');
    $('#yellowTeamList').append('<li>Simon Jones</li>');
    $('#oops').hide();
    $('#footerPlaceholder').remove();
    $('#footer').append('<p>Melanie Carroll : melanietcarroll@gmail.com</p>');
    $('#footer').css({ 'height': '24', 'font-family': 'courier' });
});