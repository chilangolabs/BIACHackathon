//
//  InitViewController.swift
//  BiacHackaton
//
//  Created by Rodrigo Lara Ruano on 20/06/16.
//  Copyright © 2016 OECD. All rights reserved.
//

import UIKit

class InitViewController: UIViewController {

  override func viewDidLoad() {
    super.viewDidLoad()
    
    // Do any additional setup after loading the view.
    self.navigationController?.setNavigationBarHidden(true, animated: true)
  }
  
  override func didReceiveMemoryWarning() {
    super.didReceiveMemoryWarning()
    // Dispose of any resources that can be recreated.
  }
  
  @IBAction func registerAction() {
    let registerViewController = RegisterViewController(nibName: "RegisterViewController", bundle: nil)
    self.navigationController?.presentViewController(registerViewController, animated: true, completion: nil)
  }
}
