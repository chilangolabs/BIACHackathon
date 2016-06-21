//
//  ProfileTableViewCell.swift
//  BiacHackaton
//
//  Created by Rodrigo Lara Ruano on 21/06/16.
//  Copyright © 2016 OECD. All rights reserved.
//

import UIKit

class ProfileTableViewCell: UITableViewCell {
  
  @IBOutlet weak var name: UILabel!
  @IBOutlet weak var type: UILabel!

  override func awakeFromNib() {
    super.awakeFromNib()
    // Initialization code
  }
  
  override func setSelected(selected: Bool, animated: Bool) {
    super.setSelected(selected, animated: animated)
    
    // Configure the view for the selected state
  }
}
