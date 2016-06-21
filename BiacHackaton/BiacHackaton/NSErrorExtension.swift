//
//  NSErrorExtension.swift
//  NubleeriPhone
//
//  Created by Erick Camacho on 11/24/14.
//  Copyright (c) 2014 nubleer. All rights reserved.
//

import Foundation

extension NSError {

  //Returns true if the cause of the error is not
  func isAPINotReachableError() -> Bool {
    return self.code == NSURLErrorBadServerResponse || self.code == NSURLErrorNotConnectedToInternet ||
    self.code == NSURLErrorCannotConnectToHost || self.code == NSURLErrorTimedOut ||
    self.code == NSURLErrorNetworkConnectionLost || self.code == NSURLErrorResourceUnavailable;
  }
  
  
  //Returns true if the server responded with a 403 status code
  func isSessionEndedError() -> Bool {
    if let userInfoDic: [NSObject: AnyObject] = userInfo {
      if let response : NSHTTPURLResponse = userInfoDic["AFNetworkingOperationFailingURLResponseErrorKey"] as? NSHTTPURLResponse {
        if (response.statusCode == 403) {
          return true
        }
      }
      if let response : NSHTTPURLResponse = userInfoDic["com.alamofire.serialization.response.error.response"] as? NSHTTPURLResponse {
        if (response.statusCode == 403) {
          return true
        }
      }
    }
    
    return false

  }
  
}