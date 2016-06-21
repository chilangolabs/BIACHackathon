//
//  RegisterViewController.swift
//  BiacHackaton
//
//  Created by Rodrigo Lara Ruano on 20/06/16.
//  Copyright © 2016 OECD. All rights reserved.
//

import UIKit

class RegisterViewController: UIViewController {
  
  @IBOutlet weak var viewName: UIView!
  @IBOutlet weak var viewLastName: UIView!
  @IBOutlet weak var viewPhone: UIView!
  @IBOutlet weak var viewEmail: UIView!
  @IBOutlet weak var viewPassword: UIView!
  @IBOutlet weak var nameField: UITextField!
  @IBOutlet weak var lastField: UITextField!
  @IBOutlet weak var phoneField: UITextField!
  @IBOutlet weak var emailField: UITextField!
  @IBOutlet weak var passwordField: UITextField!
  
  var spinner: UIActivityIndicatorView!

  override func viewDidLoad() {
    super.viewDidLoad()
    
    self.styleBorde(self.viewName)
    self.styleBorde(self.viewLastName)
    self.styleBorde(self.viewPhone)
    self.styleBorde(self.viewEmail)
    self.styleBorde(self.viewPassword)
    
    self.initSpinner()
  }
  
  override func didReceiveMemoryWarning() {
    super.didReceiveMemoryWarning()
    // Dispose of any resources that can be recreated.
  }
  
  func initSpinner() {
    self.spinner = UIActivityIndicatorView(activityIndicatorStyle: UIActivityIndicatorViewStyle.Gray)
    self.view.addSubview(self.spinner)
  }
  
  func styleBorde(view: UIView) {
    view.layer.borderWidth = 1
    view.layer.borderColor = UIColor(red: 0/255, green: 67/255, blue: 117/255, alpha: 1.0).CGColor /* #004375 */
  }
  
  @IBAction func registerAction() {
    if self.emptyValidateField([self.nameField, self.lastField, self.phoneField, self.emailField, self.passwordField]) {
      self.spinner.startAnimating()
      
      Server.register("\(self.nameField.text!) \(self.lastField.text!)", phone: self.phoneField.text!, email: self.emailField.text!, password: self.passwordField.text!, result: { (response) in
        self.spinner.stopAnimating()
        
        if response.type == .OK {
          if let responseData = response.responseObject as? NSDictionary {
//            print(responseData)
            let json = JSON(responseData)
            let dataObject = json.dictionaryValue

//            print(dataObject["accessToken"]!.string!)
            self.saveToken(dataObject["accessToken"]!.string!)
          }
          
          self.loadTabBarController()
        } else {
           UIAlertView(title: "Error", message: "El correo ya esta registrado, favor de intentar con otro", delegate: self, cancelButtonTitle: "Aceptar").show()
        }
      })
    } else {
      UIAlertView(title: "Error", message: "Uno o mas campos estan vacíos, favor de intentar de nuevo", delegate: self, cancelButtonTitle: "Aceptar").show()
    }
  }
  
  func loadTabBarController() {
    let homeViewController = HomeViewController(nibName: "HomeViewController", bundle: nil)
    homeViewController.tabBarItem = UITabBarItem(title: "Emergencia", image: UIImage(named: "ImgEmergency"), tag: 0)
    
    let profilesViewController = ProfilesViewController(nibName: "ProfilesViewController", bundle: nil)
    profilesViewController.tabBarItem = UITabBarItem(title: "Perfiles", image: UIImage(named: "ImgProfiles"), tag: 1)
    
    let firstAidViewController = FirstAidViewController(nibName: "FirstAidViewController", bundle: nil)
    firstAidViewController.tabBarItem = UITabBarItem(title: "Primeros auxilios", image: UIImage(named: "ImgFirstAid"), tag: 2)
    
    let settingsViewController = SettingsViewController(nibName: "SettingsViewController", bundle: nil)
    settingsViewController.tabBarItem = UITabBarItem(title: "Configuración", image: UIImage(named: "ImgSettings"), tag: 3)
    
    let tabBarController = UITabBarController()
    tabBarController.viewControllers = [homeViewController, profilesViewController, firstAidViewController, settingsViewController]
    tabBarController.tabBar.tintColor = UIColor(red: 54/255, green: 54/255, blue: 54/255, alpha: 1.0) /* #363636 */
    
    UIApplication.sharedApplication().delegate?.window??.rootViewController = UINavigationController(rootViewController: tabBarController)
  }
  
  func emptyValidateField(arrayFields: NSArray) -> Bool {
    var isValid = false
    
    for field in arrayFields {
      if let textField = field as? UITextField {
        let text = cleanString(textField.text!) as String
        if text.isEmpty || text == "" {
          isValid = false
          break
        } else {
          isValid = true
        }
      }
    }
    
    return isValid
  }
  
  func cleanString(originalString: String) -> String {
    let cleanString = originalString.stringByTrimmingCharactersInSet(NSCharacterSet.whitespaceAndNewlineCharacterSet())
    return cleanString
  }
  
  override func touchesBegan(touches: Set<UITouch>, withEvent event: UIEvent?) {
    self.nameField.resignFirstResponder()
    self.lastField.resignFirstResponder()
    self.phoneField.resignFirstResponder()
    self.emailField.resignFirstResponder()
    self.passwordField.resignFirstResponder()
  }
  
//  func textFieldDidEndEditing(textField: UITextField) {
//    self.nameField.resignFirstResponder()
//    self.lastField.resignFirstResponder()
//    self.phoneField.resignFirstResponder()
//    self.emailField.resignFirstResponder()
//    self.passwordField.resignFirstResponder()
//  }
}
