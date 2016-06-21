//
//  Server.swift
//  BiacHackaton
//
//  Created by Rodrigo Lara Ruano on 21/06/16.
//  Copyright © 2016 OECD. All rights reserved.
//

import Foundation

class Server {
  class func register(name: String, phone: String, email: String, password: String, result: (response: APIResponse) -> Void) {
    let urlRequest = "https://nisi-chilangolabs.rhcloud.com/v1/auth/signup"
    
    let body = ["name": name, "phone": phone, "email": email, "password": password]
    
    let request  = NSMutableURLRequest(URL: NSURL(string: urlRequest)!)
    request.HTTPMethod = "POST"
    request.allHTTPHeaderFields = ["Content-Type": "application/json"]
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
  
  class func getMedicalCard(result: (response: APIResponse) -> Void) {
    let urlRequest = "https://nisi-chilangolabs.rhcloud.com/v1/me/medicalid"
    
    if let token = self.userToken() {
      let request  = NSMutableURLRequest(URL: NSURL(string: urlRequest)!)
      request.HTTPMethod = "GET"
      request.allHTTPHeaderFields = ["X-Access-Token": token]
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
  }
  
  class func requestEmergency(medicalId: String, result: (response: APIResponse) -> Void) {
    let urlRequest = "https://nisi-chilangolabs.rhcloud.com/v1/auth/signup"
    
    let lat = NSUserDefaults.standardUserDefaults().objectForKey("lat") as! Double
    let lon = NSUserDefaults.standardUserDefaults().objectForKey("lon") as! Double
    
    let body = ["latitud": lat, "longitud": lon, "medicalId": medicalId]
    
    if let token = self.userToken() {
      let request  = NSMutableURLRequest(URL: NSURL(string: urlRequest)!)
      request.HTTPMethod = "POST"
      request.allHTTPHeaderFields = ["X-Access-Token": token, "Content-Type": "application/json"]
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
