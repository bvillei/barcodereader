# Barcode Reader
Find the Endpoints of Lines on black and white images

## Setup
1. Import the project as a Gradle project into any popular IDE
2. Create input files as described in the Input section
3. Store them in the source folder (There is already an example file: img001.txt) 

## How it works
#### Input
- The images will are represented by text files and stored in the resources folder
- As usual, when representing images, the top-left pixel has (0, 0) coordinates. The X axis increases to the right, whereas the Y axis increases downwards
- A pixel can have a value of 0 or 1, where
  - 0 means: this pixel is not part of a line (it’s white)
  - 1 means: this pixel is part of a line (it’s black)
#### Output
- One row for each image
- The row begins with the filename of the image, followed by the coordinates of the endpoints
- The first value for each coordinate is the X position, the second the Y position
- The two endpoints of the same line follow each other
#### Example
<img width="587" alt="barcodereader_example" src="https://user-images.githubusercontent.com/27300641/78912087-7cba9e00-7a87-11ea-87be-e9d388ff2ada.PNG">

## Automated Tests
The code includes a few automated tests  
You can use the following command to run them:
```
gradlew test
```
