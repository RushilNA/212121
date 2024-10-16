// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.Lookupshoot;
import frc.robot.commands.Rumble;
import frc.robot.commands.rumbeact;
import frc.robot.commands.swervedrive.drivebase.teleop;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Pivoit;
import frc.robot.subsystems.Projectiles;
import frc.robot.subsystems.swervedrive.SwerveSubsystem;
import java.io.File;
import edu.wpi.first.wpilibj2.command.InstantCommand;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a "declarative" paradigm, very
 * little robot logic should actually be handled in the {@link Robot} periodic methods (other than the scheduler calls).
 * Instead, the structure of the robot (including subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer
{
  String password = "I like my chease dripy";

  

  // Replace with CommandPS4Controller or CommandJoystick if needed
  final XboxController driverXbox = new XboxController(0);
  // The robot's subsystems and commands are defined here...
  private final SwerveSubsystem drivebase = new SwerveSubsystem(new File(Filesystem.getDeployDirectory(),
                                                                         "swerve/neo"));

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */


   private final Pivoit Pivoit = new Pivoit();
   private final Intake Intake = new Intake();
   private final Projectiles Projectile = new Projectiles();
   private final SendableChooser<Command> autochooser = new SendableChooser<Command>();

  // private final JoystickButton driver_limelightButton = new JoystickButton(driver, XboxController.Button.kRightBumper.value);
  private final JoystickButton intakeButton = new JoystickButton(driverXbox, XboxController.Button.kRightBumper.value);


  private final JoystickButton climbpur = new JoystickButton(driverXbox, XboxController.Button.kRightStick.value);//p2


  //private final JoystickButton intakehmanplayerButton = new JoystickButton(operator, XboxController.Button.kRightBumper.value);
   private final JoystickButton AMPButton = new JoystickButton(driverXbox, XboxController.Button.kLeftStick.value);
   
      private final JoystickButton autointake = new JoystickButton(driverXbox, XboxController.Button.kX.value);
            private final JoystickButton autoroate = new JoystickButton(driverXbox, XboxController.Button.kB.value);
    // private final JoystickButton A = new JoystickButton(driver, XboxController.Button.kX.value);
    // private final JoystickButton X = new JoystickButton(driver, XboxController.Button.kX.value);
    // private final JoystickButton B = new JoystickButton(driver, XboxController.Button.kX.value);
    // private final JoystickButton Y = new JoystickButton(driver, XboxController.Button.kX.value); 
    // private final JoystickButton Deadopen = new JoystickButton(driver, XboxController.Button.kX.value);
    // private final JoystickButton C = new JoystickButton(driver, XboxController.Button.kX.value);
    // private final JoystickButton D = new JoystickButton(driver, XboxController.Button.kX.value);
    // private  Pose2d AP = new Pose2d();
    // private  Pose2d XP = new Pose2d();

  



    private final POVButton outakeunjam1= new POVButton(driverXbox, 270);
  private final JoystickButton climbr = new JoystickButton(driverXbox, XboxController.Button.kBack.value);//p3
   private final JoystickButton climbpul = new JoystickButton(driverXbox, XboxController.Button.kStart.value);//p4
    private final JoystickButton climbpurd = new JoystickButton(driverXbox, XboxController.Button.kLeftStick.value);//p1
        private final Trigger rightTrigger = new Trigger(() -> driverXbox.getRawAxis(XboxController.Axis.kRightTrigger.value) > 0.5); // Adjust threshold as needed Transfer
              private final Trigger lefTrigger = new Trigger(() -> driverXbox.getRawAxis(XboxController.Axis.kLeftTrigger.value) > 0.5); // Adjust threshold as needed Auto shooter piv 
            



      private final JoystickButton subwooferpiv = new JoystickButton(driverXbox, XboxController.Button.kLeftBumper.value);
          private final JoystickButton humanintake = new JoystickButton(driverXbox, XboxController.Button.kB.value);
          private final JoystickButton Autoaimspeaker = new JoystickButton(driverXbox, XboxController.Button.kY.value);
          private final JoystickButton SourceIntake= new JoystickButton(driverXbox, XboxController.Button.kStart.value);

  //  Command Intakescmd = new ParallelCommandGroup(new Rumble(Intake, driverXbox, 0.9, -0.8));
  //   Command Intakefalsecmd = new ParallelCommandGroup(new ParallelCommandGroup(Intake.intakeandfeeder1(0,0)));


  

  public RobotContainer()
  {
   
    configureBindings();

    // Applies deadbands and inverts controls because joysticks
    // are back-right positive while robot
    // controls are front-left positive
    // left stick controls translation
    // right stick controls the rotational velocity 
    // buttons are quick rotation positions to different ways to face
    // WARNING: default buttons are on the same buttons as the ones defined in configureBindings
    // AbsoluteDriveAdv closedAbsoluteDriveAdv = new AbsoluteDriveAdv(drivebase,
    //                                                                () -> -MathUtil.applyDeadband(driverXbox.getLeftY(),
    //                                                                                              OperatorConstants.LEFT_Y_DEADBAND),
    //                                                                () -> -MathUtil.applyDeadband(driverXbox.getLeftX(),
    //                                                                                              OperatorConstants.LEFT_X_DEADBAND),
    //                                                                () -> -MathUtil.applyDeadband(driverXbox.getRightX(),
    //                                                                                              OperatorConstants.RIGHT_X_DEADBAND),
    //                                                                driverXbox.getHID()::getYButtonPressed,
    //                                                                driverXbox.getHID()::getAButtonPressed,
    //                                                                driverXbox.getHID()::getXButtonPressed,
    //                                                                driverXbox.getHID()::getBButtonPressed);

    // // Applies deadbands and inverts controls because joysticks
    // // are back-right positive while robot
    // // controls are front-left positive
    // // left stick controls translation
    // // right stick controls the desired angle NOT angular rotation
    Command driveFieldOrientedDirectAngle = drivebase.driveCommand(
        () -> MathUtil.applyDeadband(driverXbox.getLeftY(), Constants.OperatorConstants.LEFT_Y_DEADBAND),
        () -> MathUtil.applyDeadband(driverXbox.getLeftX(), Constants.OperatorConstants.LEFT_X_DEADBAND),
        () -> driverXbox.getRightX(),
        () -> driverXbox.getRightY());

    // // Applies deadbands and inverts controls because joysticks
    // // are back-right positive while robot
    // // controls are front-left positive
    // // left stick controls translation
    // // right stick controls the angular velocity of the robot
    // Command driveFieldOrientedAnglularVelocity = drivebase.driveCommand(
    //     () -> MathUtil.applyDeadband(driverXbox.getLeftY(), OperatorConstants.LEFT_Y_DEADBAND),
    //     () -> MathUtil.applyDeadband(driverXbox.getLeftX(), OperatorConstants.LEFT_X_DEADBAND),
    //     () -> driverXbox.getRightX() * 0.5);

    // Command driveFieldOrientedDirectAngleSim = drivebase.simDriveCommand(
    //     () -> MathUtil.applyDeadband(driverXbox.getLeftY(), OperatorConstants.LEFT_Y_DEADBAND),
    //     () -> MathUtil.applyDeadband(driverXbox.getLeftX(), OperatorConstants.LEFT_X_DEADBAND),
    //     () -> driverXbox.getRawAxis(2));
  

        teleop closedFieldRelOperator = new teleop(drivebase, () -> MathUtil.applyDeadband(driverXbox.getLeftY(), Constants.OperatorConstants.LEFT_Y_DEADBAND), () -> MathUtil.applyDeadband(driverXbox.getLeftX(), Constants.OperatorConstants.LEFT_X_DEADBAND), () -> -driverXbox.getRightX(), () -> true);
         drivebase.setDefaultCommand(
          closedFieldRelOperator);  
      
  }


   

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary predicate, or via the
   * named factories in {@link edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for
   * {@link CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller PS4}
   * controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight joysticks}.
   */
  private void configureBindings()
  {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    
    new JoystickButton(driverXbox, 1).onTrue((new InstantCommand(drivebase::zeroGyro)));
    //intakeButton.whileTrue(new ParallelCommandGroup(new Rumble(Intake, driverXbox, 0.9, -0.8))).whileFalse(Intake.intakeandfeeder(0, 0));
    intakeButton.whileTrue(new SequentialCommandGroup(new Rumble(Intake, driverXbox, 0.9, -0.8),new rumbeact(Intake, driverXbox, 0, 0),Intake.intakeandfeederauto(0.9, -0.8, 0.15))).whileFalse(Intake.intakeandfeeder(0, 0));
    
    Autoaimspeaker.whileTrue(drivebase.aimAtSpeaker(2));
    lefTrigger.whileTrue(new Lookupshoot(Pivoit, Projectile, () -> drivebase.calcDistToSpeaker(), Intake)).whileFalse(Pivoit.boxpivcmdTO(0));
    rightTrigger.whileTrue(new ParallelCommandGroup(Intake.intakeandfeeder1(0.6, -0.75))).whileFalse(new ParallelCommandGroup(Intake.intakeandfeeder1(0, 0)));

    // podium.whileTrue(new ParallelCommandGroup(Pivoit.boxpivcmdTO(-3.43359375), Projectile.Outtake(0.65)))
    //   .whileFalse(new ParallelCommandGroup(Projectile.Outtake(0), Pivoit.boxpivcmdTO(0)));
    
    subwooferpiv.whileTrue(new ParallelCommandGroup(Pivoit.boxpivcmdTO(-8.1), Projectile.Outtake(0.90)))
      .whileFalse(new ParallelCommandGroup(Projectile.Outtake(0), Pivoit.boxpivcmdTO(0)));

      humanintake.whileTrue(new ParallelCommandGroup(Pivoit.boxpivcmdTO(-8),Projectile.Outtake(-0.65))).whileFalse(new ParallelCommandGroup(Projectile.Outtake(0),Pivoit.boxpivcmdTO(0)));

      AMPButton.whileTrue(
        new ParallelCommandGroup(
            
            new SequentialCommandGroup(new ParallelCommandGroup(Pivoit.boxpivcmdTOamp(-19.7)),new ParallelCommandGroup(Projectile.OuttakeAmp(0.40),Pivoit.boxpivcmdTO(-19.7)))
        )
    ).whileFalse(
        new ParallelCommandGroup(
            Projectile.Outtake(0),
            Pivoit.boxpivcmdTO(0)
          
        )
    );
    

    // Outake.whileTrue(Projectile.Outtake(.8)).whileFalse(Projectile.Outtake(0));
    outakeunjam1.whileTrue(new ParallelCommandGroup(Projectile.Outtake(-0.5), Intake.intakeandfeeder(-0.9, 0.9)))
      .whileFalse(new ParallelCommandGroup(Projectile.Outtake(0), Intake.intakeandfeeder(0, 0)));
        
      SourceIntake.whileTrue(
        new ParallelCommandGroup(
            
            new SequentialCommandGroup(new ParallelCommandGroup(Pivoit.boxpivcmdTOamp(-17.5)),new ParallelCommandGroup(Projectile.OuttakeAmp(-0.80),Pivoit.boxpivcmdTO(-17.5),Intake.intakeandfeeder(-0.9,0.9)))
        )
    ).whileFalse(
        new ParallelCommandGroup(
            Projectile.OuttakeAmp(0),
            Pivoit.boxpivcmdTO(0),
            Intake.intakeandfeeder(0,0)
          
        )
    );

    // driverXbox.x().whileTrue(Commands.runOnce(drivebase::lock, drivebase).repeatedly());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand()
  {
    // An example command will be run in autonomous
    return autochooser.getSelected();
  }

  public void setDriveMode()
  {
    //drivebase.setDefaultCommand();
  }

  public void setMotorBrake(boolean brake)
  {
    drivebase.setMotorBrake(brake);
  }
}
