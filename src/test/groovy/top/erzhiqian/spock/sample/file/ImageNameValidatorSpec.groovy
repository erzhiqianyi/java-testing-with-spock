package top.erzhiqian.spock.sample.file

import spock.lang.Specification

class ImageNameValidatorSpec extends Specification {

    def "Valid images are JPG ( bad sample)"() {
        given: "an image extension checker and a jpg file"
        ImageNameValidator validator = new ImageNameValidator()
        String pictureFile = "Scenery.jpg"

        expect: "that filename is valid"
        validator.isValidImageExtension(pictureFile)

    }


    def "Valid images are JPEG ( bad sample)"() {
        given: "an image extension checker and a jpeg file"
        ImageNameValidator validator = new ImageNameValidator()
        String pictureFile = "house.jpeg"

        expect: "that filename is valid"
        validator.isValidImageExtension(pictureFile)

    }


    def "Tiff are invalid ( bad sample)"() {
        given: "an image extension checker and a tiff file"
        ImageNameValidator validator = new ImageNameValidator()
        String pictureFile = "sky.tiff"

        expect: "that filename is valid"
        !validator.isValidImageExtension(pictureFile)

    }


    def "Valid images are PNG and JPEG files"() {
        given: "an image extension checker"
        ImageNameValidator validator = new ImageNameValidator()

        expect: "that only valid filenames are accepted"
        validator.isValidImageExtension(pictrurFile) == validPictrue

        where: "sample image names are"
        pictrurFile       || validPictrue
        "scenery.jpg"     || true
        "house.jpeg"      || true
        "car.png"         || true
        "sky.tiff"        || false
        "dance_bunny.gif" || false
    }

    def "Valid images are PNG and JPEG files (enterprise version)"() {
        given: "an image extension checker"
        ImageNameValidator validator = new ImageNameValidator()

        when: "an image is checked"
        ImageExtensionCheck imageExtensionCheck = validator.examineImageExtension(pictrurFile)

        then: "expect that only valid filenames are accepted"
        imageExtensionCheck.result == validPictrue
        imageExtensionCheck.errorCode == error
        imageExtensionCheck.errorDescription == descriotion

        where: "sample image names are "
        pictrurFile       || validPictrue | error      | descriotion
        "scenery.jpg"     || true         | ""         | ""
        "house.jpeg"      || true         | ""         | ""
        "car.png"         || true         | ""         | ""
        "sky.tiff"        || false        | "ERROR002" | "Tiff files are not supported"
        "dance_bunny.gif" || false        | "ERROR999" | "Unsupported file type"

    }

    def "Tiff,gif,raw,mov and bmp are invalid extensions"(){
        given: "an image extension checker"
        ImageNameValidator validator = new ImageNameValidator()

        expect: "that only valid filenames are accepted"
        !validator.isValidImageExtension(pictureFile)

        where: "sample image names are"
        pictureFile         || _
        "screenshop.bmp"    || _
        "IMG3434.raw"       || _
        "christmas.mov"     || _
        "sky.tiff"          || _
        "dance_bunny.tiff"  || _

    }
}
