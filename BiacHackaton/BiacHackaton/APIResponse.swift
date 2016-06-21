//
//  APIResponse.swift
//  NubleeriPhone
//
//  Created by Erick Camacho on 11/24/14.
//  Copyright (c) 2014 nubleer. All rights reserved.
//

import Foundation

public enum APIResponseType: Int {
  case OK, ERROR
}

public class APIResponse: NSObject {
  
  var responseObject: AnyObject?
  
  let type: APIResponseType
  
  let error: APIError?
  
  let warning: APIWarning?
  
  init(responseObject: AnyObject?, type: APIResponseType, error: APIError?) {
    self.responseObject = responseObject
    self.type = type
    self.error = error
    self.warning = nil
  }
  
  init(responseObject: AnyObject?, type: APIResponseType, error: APIError?, warning: APIWarning?) {
    self.responseObject = responseObject
    self.type = type
    self.error = error
    self.warning = warning
  }


  public class func fromNSError(error: NSError) -> APIResponse {
    return APIResponse(responseObject: nil, type: .ERROR, error: APIError.fromNSError(error))
  }
  
  public class func fromNSErrorWithResponse(responseObject: AnyObject?, error: NSError) -> APIResponse {
    return APIResponse(responseObject: responseObject, type: .ERROR, error: APIError.fromNSError(error))
  }
  
  public class func sessionEndedResponse() -> APIResponse {
    return APIResponse(responseObject: nil, type: .ERROR, error: APIError.sessionEndedError())
  }
  
}