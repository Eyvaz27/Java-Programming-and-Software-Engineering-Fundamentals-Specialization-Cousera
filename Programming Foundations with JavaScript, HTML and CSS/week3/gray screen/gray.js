var original_image;
var grayscale_image;

function upload(){
    var fileinput = document.getElementById("finput")
    // var filename = fileinput.value;
    // alert("You chose the file " + filename);
    original_image = new SimpleImage(fileinput);
    var original_canvas = document.getElementById("original");
    original_image.drawTo(original_canvas);
}

function makeGray(){
    grayscale_image = original_image;
    for (var pixel of grayscale_image.values()) {
        var avg = (pixel.getRed() + pixel.getGreen() + pixel.getBlue())/3.0;
        pixel.setRed(avg);
        pixel.setGreen(avg);
        pixel.setBlue(avg);
    }
    var grayscale_canvas = document.getElementById("grayscale");
    grayscale_image.drawTo(grayscale_canvas);
}