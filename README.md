# RGB-to-Grayscale
The project theme consists of transforming an image from the RGB format to the Grayscale format, using the Average method. The application that is created performs this transformation through a Producer-Buffer-Consumer class structure, both Producer and Consumer inherit the Thread class.

In this scenario, the Producer is the class that receives the image that needs to be transformed, then puts the pixels in the Buffer one by one until it reaches 1/4 of the image. Once put in the buffer, the pixel is sent further to the Consumer class, where the Average algorithm is applied and the pixel that was previously in RGB format is now in Grayscale.

Reading the image is done from a file, the user can select from the available pictures which one they want to use as the application's input. In case the user enters the image name incorrectly, or the image is not found among the available ones, then the application will ask for a new image name input.
Writing the image is also done in a file, and the grayscale image will have the name Result.bmp.

For reading the image and, implicitly, the image name (from the keyboard) a hierarchy of 3 classes was implemented: ImageProcessing -> ReadImage -> ImageInfo
