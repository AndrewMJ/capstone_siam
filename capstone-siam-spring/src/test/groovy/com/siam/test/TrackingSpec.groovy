import com.siam.PcapExample
import spock.lang.Specification

class TrackingSpec extends Specification {

   def "Track the status of a connected device"(){
       given: "A monitoring system"
       PcapExample pcapExample0 = new PcapExample()

       and : "A device"
       String host = "192.168.88.90"

       when: "A user checks the status of that device"
       boolean success = pcapExample0.checkSuccess(host)
       boolean timeout = pcapExample0.checkTimeoutFlag(host)

       then: "Return true or false for Success"
       success

       and: "Return true or false for Timeout"
       !timeout
   }

   def "Show the status of a device that is not working"(){
       given: "A monitoring system"
       PcapExample pcapExample = new PcapExample();

       and: "A device"
       String host = "192.168.88.72"

       when: "A device's status is checked"
       boolean success =  pcapExample.checkSuccess(host)
       boolean timeout =  pcapExample.checkTimeoutFlag(host)
       String statement = pcapExample.returnStatement(host)

       then: "Return false for success"
       !success

       and: "Return false for timeout"
       !timeout

       and: "Return Statement that it's not working"
       statement == "System is not working"
   }

   def "Show status of a device that is shutdown"(){
       given: "A monitoring system"
       PcapExample pcapExample2 = new PcapExample();

       and: "a device"
       String host = "192.168.88.34"

       when: "A devices's status is checked"
       boolean success = pcapExample2.checkSuccess(host);
       boolean timeout = pcapExample2.checkTimeoutFlag(host);
       String statement = pcapExample2.returnStatement(host)

       then: "Return false for success"
       !success

       and: "Return true for timeout"
       timeout

       and: "Return statement that the device is shutdown down"
       statement == "System is shutdown"
   }

   def "Show status of a device that is up and running"(){
       given: "A monitoring system"
       PcapExample pcapExample3 = new PcapExample()

       and: "A device"
       String host = "192.168.88.90"

       when: "A device's status is checked"
       boolean success = pcapExample3.checkSuccess(host)
       boolean timeout = pcapExample3.checkTimeoutFlag(host)
       String statement = pcapExample3.returnStatement(host)

       then: "Return true for success"
       success

       and: "Return false for timeout"
       !timeout

       and: "Return statement that the device is up and running "
       statement == "System is up and running"

   }
}
