//
//  UIViewControllerExtension.swift
//  BiacHackaton
//
//  Created by Rodrigo Lara Ruano on 21/06/16.
//  Copyright © 2016 OECD. All rights reserved.
//

import Foundation

extension UIViewController {
  func parseJson(stringJson: String) -> NSDictionary? {
    let data: NSData = stringJson.dataUsingEncoding(NSUTF8StringEncoding)!
    
    var dataJson: NSDictionary?
    
    do {
      dataJson = try (NSJSONSerialization.JSONObjectWithData(data, options: NSJSONReadingOptions(rawValue: 0)) as? NSDictionary)
    } catch let error as NSError {
      print("Parse Json Erro: \(error)")
      dataJson = nil
    }
    
    return dataJson
  }
  
  func userToken() -> String? {
    if let token = NSUserDefaults.standardUserDefaults().objectForKey("UserToken") as? String {
      return token
    }
    
    return nil
  }
  
  func saveToken(token: String) {
    NSUserDefaults.standardUserDefaults().setObject(token, forKey: "UserToken")
    NSUserDefaults.standardUserDefaults().synchronize()
  }
}
