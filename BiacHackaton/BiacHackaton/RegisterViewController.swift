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

  override func viewDidLoad() {
    super.viewDidLoad()
    
    self.styleBorde(self.viewName)
    self.styleBorde(self.viewLastName)
    self.styleBorde(self.viewPhone)
    self.styleBorde(self.viewEmail)
    self.styleBorde(self.viewPassword)
  }
  
  override func didReceiveMemoryWarning() {
    super.didReceiveMemoryWarning()
    // Dispose of any resources that can be recreated.
  }
  
  func styleBorde(view: UIView) {
    view.layer.borderWidth = 1
    view.layer.borderColor = UIColor(red: 0/255, green: 67/255, blue: 117/255, alpha: 1.0).CGColor /* #004375 */
  }
  
  @IBAction func registerAction() {
    let homeViewController = HomeViewController(nibName: "HomeViewController", bundle: nil)
    let profilesViewController = ProfilesViewController(nibName: "ProfilesViewController", bundle: nil)
    let firstAidViewController = FirstAidViewController(nibName: "FirstAidViewController", bundle: nil)
    let settingsViewController = SettingsViewController(nibName: "SettingsViewController", bundle: nil)
    
    let tabBarController = UITabBarController()
    tabBarController.viewControllers = [homeViewController, profilesViewController, firstAidViewController, settingsViewController]
    
    UIApplication.sharedApplication().delegate?.window??.rootViewController = tabBarController
  }
}
