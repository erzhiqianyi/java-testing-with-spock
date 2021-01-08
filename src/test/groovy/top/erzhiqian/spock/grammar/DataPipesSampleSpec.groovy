package top.erzhiqian.spock.grammar

import spock.lang.Specification
import top.erzhiqian.spock.sample.file.ImageNameValidator


class DataPipesSampleSpec extends Specification {
    def "Valid images  are PNG and JPEG files only "() {
        given: "an image extension checker"
        ImageNameValidator validator = new ImageNameValidator()

        expect: "that only valid filenames are accepted"
        validator.isValidImageExtension(pictrueFile) == validPicture

        where: "sample image names are"
        pictrueFile << ["scenery.jpg", "house.jpeg", "car.png", "sky.tiff", "dance_bunny.gif"]
        validPicture << [true,true,true,false,false]
    }

}