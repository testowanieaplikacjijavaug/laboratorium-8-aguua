package MockExamples;

import org.easymock.Mock;
import org.easymock.MockType;
import org.easymock.TestSubject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.easymock.EasyMock.*;

@ExtendWith(EasyMockExtension.class)
public class FriendshipsMongoEasyMockTest {

    @TestSubject
    FriendshipsMongo friendships = new FriendshipsMongo();

    //A nice mock expects recorded calls in any order and returning null for other calls
    @Mock(type = MockType.NICE)
    FriendsCollection friends;

    @Test
    public void mockingWorksAsExpected(){
        Person joe = new Person("Joe");
        //Zapisanie zachowania - co sie ma stac
        expect(friends.findByName("Joe")).andReturn(joe);
        //Odpalenie obiektu do sprawdzenia zachowania
        replay(friends);
        assertThat(friends.findByName("Joe")).isEqualTo(joe);
    }

    @Test
    public void alexDoesNotHaveFriends(){
        assertThat(friendships.getFriendsList("Alex")).isEmpty();
    }

    @Test
    public void joeHas5Friends(){
        List<String> expected = Arrays.asList(new String[]{"Karol","Dawid","Maciej","Tomek","Adam"});
        Person joe = createMock(Person.class);
        expect(friends.findByName("Joe")).andReturn(joe);
        expect(joe.getFriends()).andReturn(expected);
        replay(friends);
        replay(joe);
        assertThat(friendships.getFriendsList("Joe")).hasSize(5).containsOnly("Karol","Dawid","Maciej","Tomek","Adam");
    }

    @Test  //1
    public void joeAndAlexAreNotFriends(){
        assertThat(friendships.areFriends("Joe", "Alex")).isFalse();
    }

    @Test  //2
    public void joeAndAlexAreFriends(){
        List <String> expected = Arrays.asList(new String[]{"Karol","Alex"});
        Person joe = createMock(Person.class);
        expect(friends.findByName("Joe")).andReturn(joe);
        expect(joe.getFriends()).andReturn(expected);
        replay(friends);
        replay(joe);
        assertThat(friendships.areFriends("Joe", "Alex")).isTrue();
    }

    @Test  //3
    public void getName(){
        Person joe = createMock(Person.class);
        expect(joe.getName()).andReturn("Joe");
        replay(joe);
        assertThat(joe.getName()).isEqualTo("Joe");
    }
    @Test //4
    public void isInRealtionBase() {
        Person joe = createMock(Person.class);
        expect(friends.findByName("Joe")).andReturn(joe);
        replay(friends);
        assertThat(friendships.isInRelation("Joe")).isTrue();
    }

    @Test //5
    public void isNotInRealtionBase() {
        assertThat(friendships.isInRelation("Joe")).isFalse();
    }

}
