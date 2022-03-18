package com.learning.springbootkotlinh2.exceptions

class CustomResponse(var status: Int? = null,
                      var message: String? = null,
                      var timeStamp: Long? = null) {
}