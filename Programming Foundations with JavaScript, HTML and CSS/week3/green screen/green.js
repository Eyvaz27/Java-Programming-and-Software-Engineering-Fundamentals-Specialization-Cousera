var foreground_image = null;
var background_image = null;
var green_threshold = 100;
var color_minimal = 80;

function uploadFG(){
    var fileinput = document.getElementById("fgimage")
    foreground_image = new SimpleImage(fileinput);
    var left_canvas = document.getElementById("foreground");
    foreground_image.drawTo(left_canvas);
}

function uploadBG(){
    var fileinput = document.getElementById("bgimage")
    background_image = new SimpleImage(fileinput);
    var right_canvas = document.getElementById("background");
    background_image.drawTo(right_canvas);
}

function createComposite(){
    if(foreground_image == null || ! foreground_image.complete()){
        alert("Foreground image is not available");
        return;
    }
    if(background_image == null || ! background_image.complete()) {
        alert("Background image is not available");
        return;
    }
    clearCanvases();
    var composite_image = new SimpleImage(foreground_image.getWidth(), foreground_image.getHeight());
    for (var pixel of foreground_image.values()) {
        var x = pixel.getX();
        var y = pixel.getY();
        if(pixel.getGreen() > green_threshold && (pixel.getRed() < color_minimal || pixel.getBlue() < color_minimal)) {
            var background_pixel = background_image.getPixel(x, y);
            composite_image.setPixel(x, y, background_pixel);
        }
        else {
            composite_image.setPixel(x, y, pixel);
        }
    }
    var left_canvas = document.getElementById("foreground");
    composite_image.drawTo(left_canvas);
}

function clearCanvases(){
    var left_canvas = document.getElementById("foreground");
    var right_canvas = document.getElementById("background");
    var left_cntx = left_canvas.getContext("2d");
    var right_cntx = right_canvas.getContext("2d");
    left_cntx.clearRect(0, 0, left_canvas.width, left_canvas.height);
    right_cntx.clearRect(0, 0, right_canvas.width, right_canvas.height);
}

