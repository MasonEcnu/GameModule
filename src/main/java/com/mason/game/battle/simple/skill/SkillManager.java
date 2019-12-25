package com.mason.game.battle.simple.skill;

import com.mason.game.battle.simple.career.Career;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mwu on 2019/12/23
 */
public class SkillManager {

  private static SkillManager instance;

  static {
    try {
      instance = new SkillManager();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static SkillManager getInstance() {
    return instance;
  }

  private SkillManager() {
  }

  public final Map<Career, List<SkillType>> CAREER_SKILL_MAP = new HashMap<Career, List<SkillType>>() {{
    put(Career.WARRIOR, new ArrayList<SkillType>() {{
      add(SkillType.NORMAL);
      add(SkillType.THUMP);
      add(SkillType.CRITICAL);
      add(SkillType.BLOCK);
    }});
    put(Career.MAGICIAN, new ArrayList<SkillType>() {{
      add(SkillType.NORMAL);
      add(SkillType.MANA_SHIELD);
      add(SkillType.FIRE_BALL);
      add(SkillType.SNOWSTORM);
    }});
    put(Career.PRIEST, new ArrayList<SkillType>() {{
      add(SkillType.NORMAL);
      add(SkillType.TURNUNDEPOST);
      add(SkillType.HOLY_SHOCK);
      add(SkillType.MANTRA_SHIELD);
    }});
  }};
}
