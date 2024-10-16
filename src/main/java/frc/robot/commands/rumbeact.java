package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;

public class rumbeact extends Command {

    private final Intake intake;
    private final XboxController joystick;
    private final double speed;
    private final double feederSpeed;
    private boolean isRumbling;
    private Timer rumbleTimer;

    public rumbeact(Intake intake, XboxController joystick, double speed, double feederSpeed) {
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
        rumbleTimer.reset();
        rumbleTimer.start();
        
        isRumbling=false;
    }

    @Override
    public void execute() {
       joystick.setRumble(RumbleType.kBothRumble, 10);  // Start rumble
    }

    @Override
    public void end(boolean interrupted) {
        
        joystick.setRumble(RumbleType.kBothRumble, 0);  // Ensure rumble is stopped
        rumbleTimer.stop();
        rumbleTimer.reset();
    }

    @Override
    public boolean isFinished() {
        return rumbleTimer.get() > 0.75;
    }
}