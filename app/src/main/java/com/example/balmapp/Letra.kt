class Letra(word: String) {

    var found = false
    var length = word.length
    var start = 0
    var end = 0
    //pone la localización de la letra teniendo en cuenta si está en horizontal o vertical y la gridlayout en la que se encuentra
    fun setLoc(tag: Int, isHorizontal: Boolean){
        start = tag
        end = if(isHorizontal) tag + length - 1 else tag + (length -1)*10
    }
    //comprueba la localización de la letra teniendo en cuenta si esta en horizontal o vertical y la gridlayout en la que se encuentra
    fun checkLoc(initTag: Int, finalTag: Int, isHorizontal: Boolean): Boolean{
        // if lengths do not match, return false
        if(isHorizontal){
            if(finalTag - initTag < length - 1){
                return false
            }
        } else {
            if((finalTag - initTag)/10 < length - 1){
                return false
            }
        }
        if(start == initTag && end == finalTag){
            return true
        }
        return false
    }
}
