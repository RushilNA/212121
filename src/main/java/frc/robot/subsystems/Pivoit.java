package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.lookuptable.setpoint;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Pivoit extends SubsystemBase{
    private TalonFX PivR = new TalonFX(30);
    private TalonFX PivL = new TalonFX(31);
    private PIDController pid = new PIDController(0.05, 0, 0);
    private Timer AutoTimer = new Timer();

    public Pivoit(){
        

    }   
    @Override
    public void periodic(){
        SmartDashboard.putNumber("Boxpiv", PivR.getPosition().getValueAsDouble());  
        SmartDashboard.putNumber("boxpivl", PivL.getPosition().getValueAsDouble());
        
    }
public void boxpivotMotor(double speed){
        PivR.set(speed);
        PivL.set(-speed);
       
       
    }
     public void lookuptable(setpoint setpoint){

        double speed = setSetpoint(setpoint.arm);
        boxpivotMotor(speed);

    }
    


    public void encoder(){
       
    
    }

    public double setSetpoint(double setpoint){
        
        pid.setSetpoint(setpoint);
        double move = pid.calculate(PivR.getPosition().getValueAsDouble());
        return move;
        
       
        

    }
      
        
       
        

    

   
    
        
  
//Teleop Box Pivot. Used for climb trap amp
    public Command boxpivcmdTO(double setpoint){
    
        
        return new Command() {
            @Override
            public void initialize() {
                // Initialization code, such as resetting encoders or PID controllers
            }
    
            @Override
            public void execute() {
                double speed = setSetpoint(setpoint); // Assuming setpid() calculates the speed based on PID
                boxpivotMotor(speed);
            }
    
            @Override
            public void end(boolean interrupted) {
                boxpivotMotor(0); // Stop the motor when the command ends or is interrupted
                //setSetpoint(0);
                

            }
            
    
            @Override
            public boolean isFinished() {
                return false;
            }
        };
    }    


       
    
     public Command boxpivcmdTO1(double setpoint){
    
        
        return new Command() {
            @Override
            public void initialize() {
                // Initialization code, such as resetting encoders or PID controllers
            }
    
            @Override
            public void execute() {
                double speed = setSetpoint(setpoint); // Assuming setpid() calculates the speed based on PID
                boxpivotMotor(speed);
            }
    
            @Override
            public void end(boolean interrupted) {
                boxpivotMotor(0); // Stop the motor when the command ends or is interrupted
                //setSetpoint(0);
                

            }
            
    
            @Override
            public boolean isFinished() {
                return false;
            }
        };
    }    


    public Command boxpivcmdAU(double setpoint){
    
        
        return new Command() {
            @Override
            public void initialize() {
                // Initialization code, such as resetting encoders or PID controllers
            }
    
            @Override
            public void execute() {
                double speed = setSetpoint(setpoint); // Assuming setpid() calculates the speed based on PID
                boxpivotMotor(speed);
            }
    
            @Override
            public void end(boolean interrupted) {
                boxpivotMotor(0);
                 // Stop the motor when the command ends or is interrupted
                //setSetpoint(0);
                

            }
            
    
            @Override
            public boolean isFinished() {
            return false;
                        }
        };
    }    

    public Command boxpivcmdAU1(double setpoint){
    
        
        return new Command() {
            @Override
            public void initialize() {
                AutoTimer.reset();
                
            }
    
            @Override
            public void execute() {
                double speed = setSetpoint(setpoint); // Assuming setpid() calculates the speed based on PID
                boxpivotMotor(speed);
            }
    
            @Override
            public void end(boolean interrupted) {
                boxpivotMotor(0); // Stop the motor when the command ends or is interrupted
                //setSetpoint(0);
                

            }
            
    
            @Override
            public boolean isFinished() {
            return false;
                        }
        };
    }    

     public Command boxpivcmdTOamp(double setpoint){
    
        
        return new Command() {
            @Override
            public void initialize() {
                // Initialization code, such as resetting encoders or PID controllers
                pid.setP(0.06);
            }
    
            @Override
            public void execute() {
                double speed = setSetpoint(setpoint); // Assuming setpid() calculates the speed based on PID
                boxpivotMotor(speed);
            }
    
            @Override
            public void end(boolean interrupted) {
                boxpivotMotor(0); // Stop the motor when the command ends or is interrupted
                //setSetpoint(0);
                

            }
    
            @Override
            public boolean isFinished() {
                return PivR.getPosition().getValueAsDouble() >= setpoint-0.5 && PivR.getPosition().getValueAsDouble()<= setpoint+0.5;
            }
        };
    }  
      

     public Command boxpivcmdTOampslow(double setpoint){
    
        
        return new Command() {
            @Override
            public void initialize() {
                // Initialization code, such as resetting encoders or PID controllers
            }
    
            @Override
            public void execute() {
                pid.setP(0.01);
                double speed = setSetpoint(setpoint); // Assuming setpid() calculates the speed based on PID
                boxpivotMotor(speed);
            }
    
            @Override
            public void end(boolean interrupted) {
                boxpivotMotor(0); // Stop the motor when the command ends or is interrupted
                //setSetpoint(0);
                

            }
    
            @Override
            public boolean isFinished() {
                return PivR.getPosition().getValueAsDouble() >= setpoint-1 && PivR.getPosition().getValueAsDouble()<= setpoint+1;
            }
        };
    }    

    public Command speed(double speed){

        return run(
            

        () -> boxpivotMotor(speed)
        );
    }


    
}
