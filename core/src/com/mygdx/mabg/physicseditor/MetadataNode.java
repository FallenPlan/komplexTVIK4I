package com.mygdx.mabg.physicseditor;

import com.badlogic.gdx.utils.XmlReader;

class MetadataNode {
  final int format;
  final float ptmRatio;

  MetadataNode(XmlReader.Element data)
  {
    format = data.getInt("format");
    ptmRatio = data.getFloat("ptm_ratio");
  }
}
