package hieudx.fpoly.myapplication.foreground

import java.io.Serializable

class Song : Serializable {
    private var name: String = ""
    private var author: String = ""
    private var img: Int = 0
    private var src: Int = 0

    var Name: String
        get() {
            return name
        }
        set(value) {
            name = value
        }
    var Author: String
        get() {
            return author
        }
        set(value) {
            author = value
        }
    var Img: Int
        get() {
            return img
        }
        set(value) {
            img = value
        }
    var Src:Int
        get() {
            return src
        }
        set(value) {src = value}


    constructor()
    constructor(name: String, author: String, img: Int, src: Int) {
        this.name = name
        this.author = author
        this.img = img
        this.src = src
    }


}