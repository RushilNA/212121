package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;

public class Rumble extends Command {

    private final Intake intake;
    private final XboxController joystick;
    private final double speed;
    private final double feederSpeed;
    private boolean isRumbling;
    private Timer rumbleTimer;

    public Rumble(Intake intake, XboxController joystick, double speed, double feederSpeed) {
        this.intake = intake;
        this.joystick = joystick;
        this.speed = speed;
        this.feederSpeed = feederSpeed;
        this.isRumbling = false;
        this.rumbleTimer = new Timer();
        addRequirements(intake);
    }

    @Override
    public void initialize() {
        rumbleTimer.stop();
        rumbleTimer.reset();
        isRumbling=false;
    }

    @Override
    public void execute() {
        if(intake.beamborken()== false){intake.set(speed, feederSpeed);}
       if (intake.beamborken()==true) {
        intake.set(0, 0 );
        
       } else {
        
       }

        if (intake.beamborken() && !isRumbling) {
            isRumbling = true;
            rumbleTimer.start();
            joystick.setRumble(RumbleType.kBothRumble, 10);  // Start rumble
        }

        if (isRumbling && rumbleTimer.hasElapsed(3.0)) {
            joystick.setRumble(RumbleType.kBothRumble, 0);  // Stop rumble after 3 seconds
            isRumbling = false;
        }
    }

    @Override
    public void end(boolean interrupted) {
        intake.set(0,0);
        joystick.setRumble(RumbleType.kBothRumble, 0);  // Ensure rumble is stopped
        rumbleTimer.stop();
        rumbleTimer.reset();
    }

    @Override
    public boolean isFinished() {
        return intake.beamborken();
    }
}