var original_image = null;
var gray_image = null;
var red_image = null;
var red_threshold = 128;

function uploadImage(){
    var fileInput = document.getElementById("img_original")
    var display_canvas = document.getElementById("display")
    original_image = new SimpleImage(fileInput)
    original_image.drawTo(display_canvas)
}

function colorGray(){
    if(imageIsLoaded(original_image)){
        alert("Original image is not loaded successfully")
        return;
    }
    gray_image = new SimpleImage(original_image.getWidth(), original_image.getHeight());
    for (var pixel of original_image.values()){
        var x = pixel.getX();
        var y = pixel.getY();
        var gray_value = (pixel.getRed()+pixel.getBlue()+pixel.getGreen())/3;
        gray_image.setRed(x, y, gray_value);
        gray_image.setGreen(x, y, gray_value);
        gray_image.setBlue(x, y, gray_value);
    }
    var display_canvas = document.getElementById("display")
    gray_image.drawTo(display_canvas) 
}

function colorReset(){
    if(imageIsLoaded(original_image)){
        alert("Original image is not loaded successfully")
        return;
    }
    var display_canvas = document.getElementById("display")
    original_image.drawTo(display_canvas) 
}

function colorRed(){
    if(imageIsLoaded(original_image)){
        alert("Original image is not loaded successfully")
        return;
    }
    red_image = new SimpleImage(original_image.getWidth(), original_image.getHeight());
    for (var pixel of original_image.values()){
        var x = pixel.getX();
        var y = pixel.getY();
        if(pixel.getRed()<red_threshold){
            red_image.setRed(x, y, pixel.getRed()*2);
            red_image.setGreen(x, y, 0);
            red_image.setBlue(x, y, 0);
        }
        else{
            red_image.setRed(x, y, 255);
            red_image.setGreen(x, y, pixel.getGreen()*2-255);
            red_image.setBlue(x, y, pixel.getBlue()*2-255);
        }
    }
    var display_canvas = document.getElementById("display")
    red_image.drawTo(display_canvas) 
}

function imageIsLoaded(image_object){
    if(image_object == null || ! image_object.complete()){
        return true}
    else{
        return false;
    }
}