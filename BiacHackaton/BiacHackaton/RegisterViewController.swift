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
    
    UIApplication.sharedApplication().delegate?.window??.rootViewController = tabBarController
  }
}
