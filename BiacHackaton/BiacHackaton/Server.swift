//
//  Server.swift
//  BiacHackaton
//
//  Created by Rodrigo Lara Ruano on 21/06/16.
//  Copyright © 2016 OECD. All rights reserved.
//

import Foundation

class Server {
  class func register(name: String, phone: String, email: String, password: String) {
    let urlRequest = "https://nisi-chilangolabs.rhcloud.com/v1/auth/signup"
    
    let body = ["name": name, "phone": phone, "email": email, "password": password]
    
    let request  = NSMutableURLRequest(URL: NSURL(string: urlRequest)!)
    request.HTTPMethod = "POST"
    request.HTTPBody = try! NSJSONSerialization.dataWithJSONObject(body, options: [])
    request.cachePolicy = .UseProtocolCachePolicy
    request.timeoutInterval = 60
    
    let operation = AFHTTPRequestOperation(request: request)
    operation.responseSerializer = AFJSONResponseSerializer()
    operation.setCompletionBlockWithSuccess({ (operation: AFHTTPRequestOperation!, response: AnyObject!) -> Void in
      result(response: APIResponse(responseObject: response, type: .OK, error: nil))
    }) { (operation: AFHTTPRequestOperation!, error: NSError!) -> Void in
      result(response: APIResponse.fromNSError(error))
    }
    operation.start()
  }
  
  class func sendMedicalCard() {
    
  }
  
  class func getMedicalCard() {
    
  }
  
  class func userToken() -> String? {
    if let token = NSUserDefaults.standardUserDefaults().objectForKey("UserToken") as? String {
      return token
    }
    
    return nil
  }
  
  class func saveToken(token: String) {
    NSUserDefaults.standardUserDefaults().setObject(token, forKey: "UserToken")
    NSUserDefaults.standardUserDefaults().synchronize()
  }
}
