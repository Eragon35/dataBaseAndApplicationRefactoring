package models

trait UuidHelper {
    def randomUuid = java.util.UUID.randomUUID.toString.replaceAll("-", "")
}

object UuidHelper extends UuidHelper
