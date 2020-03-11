package tracks.algorithms.greedy;

public class CanJump {

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};
        System.out.println(new CanJump().canJump(nums));
        int[] nums2 = {3, 2, 1, 0, 4};
        System.out.println(new CanJump().canJump(nums2));
    }

    public boolean canJump(int[] nums) {
        return canJump(nums, nums.length - 1);

    }

    private boolean canJump(int[] nums, int finalPos) {
        if (finalPos == 0)
            return true;
        for (int i = finalPos - 1; i >= 0; i--) {
            if (nums[i] >= (finalPos - i))
                return canJump(nums, i);
        }
        return false;
    }
}
