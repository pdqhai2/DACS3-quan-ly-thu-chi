<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

use App\Models\User;
use App\Models\Income;
use App\Models\Intype;
use App\Models\Outcome;
use App\Models\Outtype;


class AccountController extends Controller
{
    //
    public function login(Request $request) {
        $phone = $request->user_phone;
        $password = md5($request->user_pass);
        $user = User::where(["user_phone" => $phone, "user_pass" => $password])->first();
        if (isset($user)) {
            return response()->json([
                'success' => true,
                'user' => $user
            ]);
        }
        else {
            return response()->json([
                'success' => false,
                'user' => null
            ]);
        }
    }

    public function register(Request $request) {
        $checkExist = User::where("user_phone", $request->user_phone)->count();
        if ($checkExist==0) {
            $user = new User();
            $user->user_phone = $request->user_phone;
            $user->user_pass = md5($request->user_pass);
            if (isset($request->user_name)) {
                $user->user_name = $request->user_name;
            } else {
                $user->user_name = "";
            }
            $user->save();
            return response()->json([
                'success' => true,
                'user' => $user
            ]);
        } else {
            return response()->json([
                'success' => false,
                'user' => null
            ]);
        }
    }

    public function infor(Request $request) {
        $user_id = $request->user_id;
        $user = User::where(["user_id" => $user_id])->first();
        if (isset($user)) {
            return response()->json([
                'success' => true,
                'user' => $user
            ]);
        }
        else {
            return response()->json([
                'success' => false,
                'user' => null
            ]);
        }
    }
}
