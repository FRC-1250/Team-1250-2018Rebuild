package frc.robot.utilities;

public class CAN_devicefaults {
    public String stickyfault;
    public int can_id;

    public CAN_devicefaults(String stickyFault, int can_id){
        this.stickyfault = stickyFault;
        this.can_id = can_id;
    }
}