//
//  APIError.swift
//  NubleeriPhone
//
//  Created by Erick Camacho on 11/24/14.
//  Copyright (c) 2014 nubleer. All rights reserved.
//

import Foundation

public class APIError: NSObject {
  
  let code: String?
  
  let message: String?
  
  init(code:String, message: String) {
    self.code = code
    self.message = message
  }
  
  convenience init(message: String) {
    let apiErrorCodes = APIErrorCodes()
    self.init(code:apiErrorCodes.GENERIC_ERROR_CODE, message: message)
  }
  
  public class func fromNSError(error: NSError) -> APIError {
    if (error.isAPINotReachableError()) {
      return apiNotReachableError()
    }
    if (error.isSessionEndedError()) {
      return sessionEndedError();
    }
    return apiNotReachableError()
  }
  
  
  public class func apiNotReachableError() -> APIError {
    let apiErrorCodes = APIErrorCodes()
    return APIError(code: apiErrorCodes.API_NOT_REACHABLE_CODE, message: apiErrorCodes.API_NOT_REACHABLE_MESSAGE)
  }
  
  public class func sessionEndedError() -> APIError {
    let apiErrorCodes = APIErrorCodes()
    return APIError(code: apiErrorCodes.SESSION_ENDED_CODE, message: apiErrorCodes.SESSION_ENDED_MESSAGE)
  }
  
  public class func genericErrorWithError() -> APIError {
    let apiErrorCodes = APIErrorCodes()
    return APIError(code: apiErrorCodes.GENERIC_ERROR_CODE, message: apiErrorCodes.GENERIC_ERROR_MESSAGE)
  }
  
  public func isSessionEndedError() -> Bool {
    return self.code == APIErrorCodes().SESSION_ENDED_CODE
  }
  
  public func isApiNotReachableError() -> Bool {
    return self.code == APIErrorCodes().API_NOT_REACHABLE_CODE
  }
  
}