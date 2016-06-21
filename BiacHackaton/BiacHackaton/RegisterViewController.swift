//
//  RegisterViewController.swift
//  BiacHackaton
//
//  Created by Rodrigo Lara Ruano on 20/06/16.
//  Copyright © 2016 OECD. All rights reserved.
//

import UIKit

class RegisterViewController: UIViewController {

  override func viewDidLoad() {
    super.viewDidLoad()
    
    let gradient: CAGradientLayer = CAGradientLayer()
    gradient.frame = view.bounds
    gradient.colors = [UIColor.whiteColor().CGColor, UIColor.blackColor().CGColor]
    self.view.layer.insertSublayer(gradient, atIndex: 0)
  }
  
  override func didReceiveMemoryWarning() {
    super.didReceiveMemoryWarning()
    // Dispose of any resources that can be recreated.
  }
}
