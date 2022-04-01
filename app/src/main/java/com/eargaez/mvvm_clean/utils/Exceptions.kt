package com.eargaez.mvvm_clean.utils

import java.io.IOException

class ApiException( message: String ) : IOException(message)
class UnauthorizedException( message: String ) : IOException(message)
class NoInternetException( message: String ) : IOException(message)