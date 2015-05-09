/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.pokerclock;

import ispok.dto.LevelDto;
import ispok.dto.PayoutPlaceDto;
import ispok.dto.PayoutStructureDto;
import ispok.dto.TournamentDto;
import ispok.dto.TournamentSessionDto;
import ispok.dto.TournamentStructureDto;
import ispok.dto.VisitorDto;
import ispok.service.TournamentService;
import ispok.service.TournamentSessionService;
import ispok.service.VisitorService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
//
@Configurable(preConstruction = true)
//
public class TournamentController {

    @Autowired
    private VisitorService visitorService;
    @Autowired
    private TournamentSessionService tournamentSessionService;
    @Autowired
    private TournamentService tournamentService;

    private TournamentDto tournamentDto;
    private TournamentStructureDto tournamentStructureDto;
    private List<LevelDto> levelDtos = new ArrayList<>(20);
    private PayoutStructureDto payoutStructureDto;
    private List<PayoutPlaceDto> payoutPlaceDtos;
    private List<TournamentSessionDto> tournamentSessionDtos;
    private List<VisitorDto> ingamePlayerDtos;
    private List<VisitorDto> sitoutPlyerDtos;

    private LevelDto currentLevel;
    private int currentLevelDuration_s;
    private int time_s;

    private boolean levelBreak;
    private boolean paused;
    private int entries;

    private float prizePool;

    private float money;

    private Timer timer = new Timer("TimeCounter");
    private TimerTask timerCounter;

    public TournamentController() {

        levelBreak = false;
        paused = false;
        entries = 0;
        prizePool = 0;
        currentLevelDuration_s = 0;
        time_s = currentLevelDuration_s;
        currentLevel = new LevelDto();
        ingamePlayerDtos = new ArrayList<>(50);
        sitoutPlyerDtos = new ArrayList<>(50);
        tournamentSessionDtos = new ArrayList<>(50);
        payoutPlaceDtos = new ArrayList<>(20);
        tournamentDto = null;

        timerCounter = new TimerTask() {
            @Override
            public void run() {
                decTime();
            }
        };
    }

    /**
     * Get the value of levelBreak
     *
     * @return the value of levelBreak
     */
    public boolean isLevelBreak() {
        return levelBreak;
    }

    /**
     * Set the value of levelBreak
     *
     * @param levelBreak new value of levelBreak
     */
    public synchronized void setLevelBreak(boolean levelBreak) {
        this.levelBreak = levelBreak;
    }

    public synchronized boolean addPlayer(Long playerId) {
        VisitorDto playerDto = visitorService.getVisitorById(playerId);
        for (VisitorDto vdtDto : ingamePlayerDtos) {
            if (playerDto.getId().equals(vdtDto.getId())) {
                return false;
            }
        }
        for (VisitorDto vdtDto : sitoutPlyerDtos) {
            if (playerDto.getId().equals(vdtDto.getId())) {
                return false;
            }
        }
        ingamePlayerDtos.add(playerDto);
        entries++;
        computeMoney();

        for (int i = 0; i < tournamentSessionDtos.size(); i++) {
            TournamentSessionDto tsd = tournamentSessionDtos.get(i);
            int place = tsd.getPlace() + 1;
            float money = 0;
            for (PayoutPlaceDto ppd : payoutPlaceDtos) {
                if (ppd.getPlace() == place) {
                    money = ppd.getMoney();
                }
            }
            tsd.setPlace(place);
            tsd.setMoney(money);
        }
        return true;
    }

    public synchronized boolean removePlayer(Long id) {
        VisitorDto pDto = null;
        for (int i = 0; i < ingamePlayerDtos.size(); i++) {
            if (ingamePlayerDtos.get(i).getId().equals(id)) {
                ingamePlayerDtos.remove(i);
                entries--;
                computeMoney();
                return true;
            }
        }

        for (int i = 0; i < sitoutPlyerDtos.size(); i++) {
            VisitorDto playeDtoToRemove = sitoutPlyerDtos.get(i);
            if (playeDtoToRemove.getId().equals(id)) {
                sitoutPlyerDtos.remove(i);
                TournamentSessionDto tsdToRemove = null;
                for (int j = 0; j < tournamentSessionDtos.size(); j++) {
                    tsdToRemove = tournamentSessionDtos.get(j);
                    if (tsdToRemove.getVisitorId().equals(id)) {
                        tournamentSessionDtos.remove(j);
                        break;
                    }
                }
                for (TournamentSessionDto tsd : tournamentSessionDtos) {
                    if (tsd.getPlace() > tsdToRemove.getPlace()) {
                        tsd.setPlace(tsd.getPlace() - 1);
                        for (PayoutPlaceDto ppd : payoutPlaceDtos) {
                            if (tsd.getPlace() == ppd.getPlace()) {
                                tsd.setMoney(ppd.getMoney());
                                break;
                            }
                        }
                    }
                }
                entries--;
                computeMoney();
                return true;
            }
        }
        return false;
    }

    private synchronized void decTime() {
        time_s--;
        // time overflow
        if (time_s <= 0) {

            setNextCounter();

//            // check for break
//            if (levelBreak == false) {
//                int breakDuration_min = currentLevel.getBreakDuration();
//
//                if (breakDuration_min > 0) {
//                    currentLevelDuration_s = breakDuration_min * 60;
//                    time_s = currentLevelDuration_s;
//                    levelBreak = true;
//                    return;
//                }
//            }
//            // set next level
//            if (currentLevel.getNumber() <= levelDtos.size()) {
//                currentLevel = levelDtos.get(currentLevel.getNumber());
//                currentLevelDuration_s = currentLevel.getDuration() * 60;
//                time_s = currentLevelDuration_s;
//                levelBreak = false;
//            }
        }
    }

    /**
     * Get the value of levelDtos
     *
     * @return the value of levelDtos
     */
    public List<LevelDto> getLevels() {
        return levelDtos;
    }

    /**
     * Set the value of levelDtos
     *
     * @param levels new value of levelDtos
     */
    public synchronized void setLevels(List<LevelDto> levels) {
        this.levelDtos = levels;
    }

    /**
     * Get the value of tournamentStructureDto
     *
     * @return the value of tournamentStructureDto
     */
    public TournamentStructureDto getTournamentStructureDto() {
        return tournamentStructureDto;
    }

    /**
     * Set the value of tournamentStructureDto
     *
     * @param tournamentStructureDto new value of tournamentStructureDto
     */
    public synchronized void setTournamentStructureDto(TournamentStructureDto tournamentStructureDto) {
        this.tournamentStructureDto = tournamentStructureDto;
    }

    /**
     * Get the value of tournamentDto
     *
     * @return the value of tournamentDto
     */
    public TournamentDto getTournamentDto() {
        return tournamentDto;
    }

    /**
     * Set the value of tournamentDto
     *
     * @param tournamentDto new value of tournamentDto
     */
    public synchronized void setTournamentDto(TournamentDto tournamentDto) {
        this.tournamentDto = tournamentDto;
    }

//    public Long getTournamentId() {
//        return tournamentDto.getId();
//    }
//    public int getPlayersCount() {
//        return playerDtos.size();
//    }
    public int getTime_s() {
        return time_s;
    }

    public int getLevelNumber() {
        return currentLevel.getNumber();
    }

    public LevelDto getCurrentLevel() {
        return currentLevel;
    }

    public boolean containsPlayer(Long id) {
        for (VisitorDto visitorDto : ingamePlayerDtos) {
            if (visitorDto.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public int getEntries() {
        return entries;
    }

    public List<VisitorDto> getIngamePlayers() {
        return ingamePlayerDtos;
    }

    public synchronized List<VisitorDto> getSitoutPlayers() {
//        return sitoutPlyerDtos;
//        Collections.sort();
        return new ArrayList<>(sitoutPlyerDtos);
    }

//    public List<Sitout> getSitouts() {
//        List<Sitout> sitouts = new ArrayList<>(tournamentSessionDtos.size());
//        for (int i = 0; i < sitoutPlyerDtos.size(); i++) {
//            sitouts.add(new Sitout(sitoutPlyerDtos.get(i), tournamentSessionDtos.get(i)));
//        }
//        return sitouts;
//    }
    public TournamentSessionDto getTournamentSessionDto(Long visitorId) {
        for (TournamentSessionDto tsd : tournamentSessionDtos) {
            if (tsd.getVisitorId() == visitorId) {
                return tsd;
            }
        }
        return null;
    }

    public List<TournamentSessionDto> getTournamentSessionDtos() {
        return tournamentSessionDtos;
    }

    public synchronized void start() {
        timerCounter = new TimerTask() {
            @Override
            public void run() {
                decTime();
            }
        };
        timer.scheduleAtFixedRate(timerCounter, 1000, 1000);
    }

    public synchronized void pause() {
        timerCounter.cancel();
    }

    public synchronized void setPrevCounter() {
        int currentLevelNumber = currentLevel.getNumber();
        // check for break
        if (levelBreak == false) {
            if (currentLevelNumber == 1) {
                currentLevel = new LevelDto();
                time_s = 0;
                currentLevelDuration_s = 0;
            } else {
                currentLevel = levelDtos.get(currentLevelNumber - 2);
                int breakDuration_min = currentLevel.getBreakDuration();
                if (breakDuration_min > 0) {
                    currentLevelDuration_s = breakDuration_min * 60;
                    time_s = currentLevelDuration_s;
                    levelBreak = true;
                }
            }
            return;
        } else {
            currentLevelDuration_s = currentLevel.getDuration() * 60;
            time_s = currentLevelDuration_s;
            levelBreak = false;
        }
    }

    public synchronized void setNextCounter() {
        // check for break
        if (levelBreak == false) {
            int breakDuration_min = currentLevel.getBreakDuration();

            if (breakDuration_min > 0) {
                currentLevelDuration_s = breakDuration_min * 60;
                time_s = currentLevelDuration_s;
                levelBreak = true;
                return;
            }
        }
        // set next level
        if (currentLevel.getNumber() <= levelDtos.size()) {
            currentLevel = levelDtos.get(currentLevel.getNumber());
            currentLevelDuration_s = currentLevel.getDuration() * 60;
            time_s = currentLevelDuration_s;
            levelBreak = false;
        }
    }

    public synchronized void setPayoutStructure(PayoutStructureDto payoutStructureDto) {
        this.payoutStructureDto = payoutStructureDto;
    }

    public PayoutStructureDto getPayoutStructure() {
        return payoutStructureDto;
    }

    public synchronized void setPayoutPlaces(List<PayoutPlaceDto> payoutPlaceDtos) {
        this.payoutPlaceDtos = payoutPlaceDtos;
        for (PayoutPlaceDto ppd : payoutPlaceDtos) {
            for (TournamentSessionDto tsd : tournamentSessionDtos) {
                if (ppd.getPlace() == tsd.getPlace()) {
                    tsd.setMoney(ppd.getMoney());
                }
            }
        }
    }

    public List<PayoutPlaceDto> getPayoutPlaces() {
        return payoutPlaceDtos;
    }

    public float getPrizePool() {
        return prizePool;
    }

    public synchronized void setPrizePool(float prizePool) {
        this.prizePool = prizePool;
    }

    public synchronized int getRebuys() {
        return tournamentDto.getRebuys();
    }

    public synchronized void setRebuys(int count) {
        tournamentDto.setRebuys(count);
        computeMoney();
    }

    public synchronized int getAddons() {
        return tournamentDto.getAddons();
    }

    public synchronized void setAddons(int count) {
        tournamentDto.setAddons(count);
        computeMoney();
    }

    public synchronized void sitoutPlayer(Long playerId) {
        VisitorDto visitorDto = null;
        int place = ingamePlayerDtos.size();
        for (int i = 0; i < ingamePlayerDtos.size(); i++) {
            VisitorDto vd = ingamePlayerDtos.get(i);
            if (vd.getId().equals(playerId)) {
                visitorDto = vd;
                ingamePlayerDtos.remove(i);
                break;
            }
        }
        if (visitorDto != null) {
            TournamentSessionDto tournamentSessionDto = new TournamentSessionDto();
            tournamentSessionDto.setTournamentId(tournamentDto.getId());
            tournamentSessionDto.setVisitorId(visitorDto.getId());
            tournamentSessionDto.setPlace(place);
            tournamentSessionDto.setSitout(new Date());
            tournamentSessionDto.setMoney(0);
            for (PayoutPlaceDto ppd : payoutPlaceDtos) {
                if (ppd.getPlace() == place) {
                    tournamentSessionDto.setMoney(ppd.getMoney());
                    break;
                }
            }
            tournamentSessionDtos.add(tournamentSessionDto);
            sitoutPlyerDtos.add(visitorDto);
        }
    }

    public synchronized void stop() {
        for (TournamentSessionDto tsd : tournamentSessionDtos) {
            tournamentSessionService.save(tsd);
        }
        tournamentDto.setFinish(new Date());
        tournamentService.save(tournamentDto);
    }

    private void computeMoney() {
        float buyin = tournamentDto.getBuyin();
        float addon = tournamentDto.getAddon();
        int rebuys = tournamentDto.getRebuys();
        int addons = tournamentDto.getAddons();
        money = entries * buyin + addon * addons + rebuys * buyin;
    }

    public float getMoney() {
        return money;
    }

    public synchronized boolean playerUp(Long id) {
        TournamentSessionDto tsd1 = null, tsd2 = null;
        for (TournamentSessionDto tsd : tournamentSessionDtos) {
            if (tsd.getVisitorId().equals(id)) {
                tsd1 = tsd;
            }
        }
        if (tsd1 == null) {
            return false;
        }
        for (TournamentSessionDto tsd : tournamentSessionDtos) {
            if (tsd.getPlace() == tsd1.getPlace() - 1) {
                tsd2 = tsd;
            }
        }
        if (tsd2 == null) {
            return false;
        }
        int place = tsd1.getPlace();
        float prizeMoneyTmp = tsd1.getMoney();
        tsd1.setPlace(place - 1);
        tsd2.setPlace(place);
        tsd1.setMoney(tsd2.getMoney());
        tsd2.setMoney(prizeMoneyTmp);
        return true;
    }

    public synchronized boolean playerDown(Long id) {
        TournamentSessionDto tsd1 = null, tsd2 = null;
        for (TournamentSessionDto tsd : tournamentSessionDtos) {
            if (tsd.getVisitorId().equals(id)) {
                tsd1 = tsd;
            }
        }
        if (tsd1 == null) {
            return false;
        }
        for (TournamentSessionDto tsd : tournamentSessionDtos) {
            if (tsd.getPlace() == tsd1.getPlace() + 1) {
                tsd2 = tsd;
            }
        }
        if (tsd2 == null) {
            return false;
        }
        int place = tsd1.getPlace();
        float prizeMoneyTmp = tsd1.getMoney();
        tsd1.setPlace(place + 1);
        tsd2.setPlace(place);
        tsd1.setMoney(tsd2.getMoney());
        tsd2.setMoney(prizeMoneyTmp);
        return true;
    }
}
