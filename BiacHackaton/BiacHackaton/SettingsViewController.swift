//
//  SettingsViewController.swift
//  BiacHackaton
//
//  Created by Rodrigo Lara Ruano on 21/06/16.
//  Copyright © 2016 OECD. All rights reserved.
//

import UIKit

class SettingsViewController: UIViewController {

  override func viewDidLoad() {
    super.viewDidLoad()
    
    // Do any additional setup after loading the view.
  }
  
  override func didReceiveMemoryWarning() {
    super.didReceiveMemoryWarning()
    // Dispose of any resources that can be recreated.
  }
  
  override func viewWillAppear(animated: Bool) {
    self.title = "Configuración"
  }
}
